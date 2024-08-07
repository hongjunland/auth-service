package com.authmodule.user.application.service;

import com.authmodule.common.jwt.JwtToken;
import com.authmodule.user.adapter.in.web.response.LoginResponse;
import com.authmodule.user.application.port.in.command.LoginCommand;
import com.authmodule.user.application.port.out.LoadUserPort;
import com.authmodule.user.application.port.out.LoginPort;
import com.authmodule.user.application.port.out.PasswordEncoderPort;
import com.authmodule.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("LoginServiceTest 테스트")
@ExtendWith(SpringExtension.class)
class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;
    @Mock
    private LoadUserPort loadUserPort;
    @Mock
    private PasswordEncoderPort passwordEncoderPort;
    @Mock
    private LoginPort loginPort;


    @DisplayName("로그인 테스트")
    @Test
    void loginWithValidCredentialsReturnsLoginResponse() {
        String email = "zxc123@naver.com";
        String rawPassword = "rawPassword";
        String encodedPassword = "encodedPassword";

        LoginCommand loginCommand = LoginCommand.builder()
                .email(email)
                .password(rawPassword)
                .build();
        // given
        User user = User.builder()
                .id(new User.UserId(1L))
                .name("홍길동")
                .password(encodedPassword)
                .email(email)
                .build();
        JwtToken jwtToken = JwtToken.builder()
                .accessToken("Bearer accessToken")
                .refreshToken("refreshToken")
                .expiration(Instant.now().plusMillis(3600000L))
                .build();
//        Authentication auth = mock(Authentication.class);
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginCommand.getEmail(), loginCommand.getPassword());
        when(loadUserPort.loadByEmail(loginCommand.email())).thenReturn(user);
        when(passwordEncoderPort.matches(rawPassword, encodedPassword)).thenReturn(true);
        when(loginPort.login(loginCommand.email(), loginCommand.password())).thenReturn(jwtToken);
        // When
        LoginResponse loginResponse = loginService.login(loginCommand);

        // Then
        verify(loadUserPort, times(1)).loadByEmail(email);
        verify(passwordEncoderPort, times(1)).matches(loginCommand.password(), user.getPassword());
        assertEquals(loginResponse.accessToken(), jwtToken.getAccessToken());
        assertEquals(loginResponse.refreshToken(), jwtToken.getRefreshToken());
        assertEquals(loginResponse.expiration(), jwtToken.getExpiration().toString());
    }
}
