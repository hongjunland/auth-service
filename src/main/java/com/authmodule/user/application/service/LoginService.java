package com.authmodule.user.application.service;

import com.authmodule.common.annotaion.*;
import com.authmodule.common.jwt.JwtToken;
import com.authmodule.user.application.port.in.*;
import com.authmodule.user.application.port.in.command.LoginCommand;
import com.authmodule.user.application.port.out.*;
import com.authmodule.user.adapter.in.web.response.LoginResponse;
import com.authmodule.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@UseCase
@Transactional
class LoginService implements LoginUseCase {
    private final LoadUserPort loadUserPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final LoginPort loginPort;

    @Override
    public LoginResponse login(LoginCommand command) {
        User user = loadUserPort.loadByEmail(command.email());
        passwordEncoderPort.matches(command.password(), user.getPassword());
        JwtToken jwtToken = loginPort.login(command.email(), command.password());

        return LoginResponse.builder()
                .accessToken(jwtToken.getAccessToken())
                .refreshToken(jwtToken.getRefreshToken())
                .expiration(jwtToken.getExpiration().toString())
                .build();
    }

    @Override
    public void logout(Long userId) {
        loginPort.logout(userId);
    }
}
