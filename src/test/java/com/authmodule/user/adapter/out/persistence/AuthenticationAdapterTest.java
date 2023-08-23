package com.authmodule.user.adapter.out.persistence;


import com.authmodule.common.utils.Token;
import com.authmodule.common.utils.TokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("AuthenticationAdapterTest 테스트")
@ExtendWith(MockitoExtension.class)
class AuthenticationAdapterTest {
    @InjectMocks
    private AuthenticationAdapter authenticationAdapter;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenProvider tokenProvider;

    @DisplayName("로그인")
    @Test
    public void login(){
        // Given
        Authentication auth = mock(Authentication.class);
        Token token = mock(Token.class);
        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(tokenProvider.generateToken(auth)).thenReturn(token);

        // When
        authenticationAdapter.login("zxc123@naver.com", "zxczxczxc");

        // Then
        verify(authenticationManager, times(1)).authenticate(any());
        verify(tokenProvider, times(1)).generateToken(auth);
    }
}
