package com.authmodule.user.adapter.out.persistence;

import com.authmodule.common.annotaion.PersistenceAdapter;
import com.authmodule.common.jwt.JwtToken;
import com.authmodule.common.jwt.JwtProvider;
import com.authmodule.common.jwt.refresh.repository.RefreshTokenRepository;
import com.authmodule.user.application.port.out.LoginPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.Duration;

@PersistenceAdapter
@RequiredArgsConstructor
class AuthenticationAdapter implements LoginPort {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider JwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    @Override
    public JwtToken login(String email, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authenticationManager.authenticate(authToken);
        JwtToken jwtToken = JwtProvider.generateToken(auth);
        refreshTokenRepository.saveToken(auth.getName(),
                jwtToken.getRefreshToken(),
                Duration.ofMillis(jwtProvider.getProperties().getExpiration().getRefresh())
        );
        return jwtToken;
    }

    @Override
    public void logout(Long userId) {
        refreshTokenRepository.deleteToken(Long.toString(userId));
    }
}
