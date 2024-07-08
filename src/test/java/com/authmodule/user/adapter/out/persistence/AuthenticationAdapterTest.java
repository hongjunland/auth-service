package com.authmodule.user.adapter.out.persistence;


import com.authmodule.common.jwt.JwtToken;
import com.authmodule.common.jwt.JwtProvider;
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
    private JwtProvider JWtProvider;

    @DisplayName("로그인")
    @Test
    public void login(){
        // Given
        Authentication auth = mock(Authentication.class);
        JwtToken jwtToken = mock(JwtToken.class);
        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(JWtProvider.generateToken(auth)).thenReturn(jwtToken);

        // When
        authenticationAdapter.login("zxc123@naver.com", "zxczxczxc");

        // Then
        verify(authenticationManager, times(1)).authenticate(any());
        verify(JWtProvider, times(1)).generateToken(auth);
    }
}
