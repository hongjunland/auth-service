package com.authmodule.user.application.service;

import com.authmodule.common.annotaion.*;
import com.authmodule.common.utils.Token;
import com.authmodule.user.application.port.in.*;
import com.authmodule.user.application.port.in.command.LoginCommand;
import com.authmodule.user.application.port.out.*;
import com.authmodule.user.adapter.in.web.response.LoginResponse;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import javax.transaction.Transactional;
@RequiredArgsConstructor
@UseCase
@Transactional
class LoginService implements LoginUseCase {
    private final LoadUserPort loadUserPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final LoginPort loginPort;

    @Override
    public LoginResponse login(LoginCommand command) {
        User user = loadUserPort.loadByEmail(command.getEmail());
        passwordEncoderPort.matches(command.getPassword(), user.getPassword());
        Token jwtToken = loginPort.login(command.getEmail(), command.getPassword());

        return LoginResponse.builder()
                .accessToken(jwtToken.getAccessToken())
                .refreshToken(jwtToken.getRefreshToken())
                .expiration(jwtToken.getExpiration().toString())
                .build();
    }
}
