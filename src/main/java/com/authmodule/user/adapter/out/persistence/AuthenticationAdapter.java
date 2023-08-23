package com.authmodule.user.adapter.out.persistence;

import com.authmodule.common.annotaion.PersistenceAdapter;
import com.authmodule.common.utils.Token;
import com.authmodule.common.utils.TokenProvider;
import com.authmodule.user.application.port.out.LoginPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@PersistenceAdapter
@RequiredArgsConstructor
class AuthenticationAdapter implements LoginPort {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Override
    public Token login(String email, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authenticationManager.authenticate(authToken);
        Token token = tokenProvider.generateToken(auth);
        return token;
    }
}
