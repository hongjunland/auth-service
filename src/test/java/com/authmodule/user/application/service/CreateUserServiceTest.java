package com.authmodule.user.application.service;

import com.authmodule.user.application.port.in.command.CreateUserCommand;
import com.authmodule.user.application.port.out.CreateUserPort;
import com.authmodule.user.application.port.out.PasswordEncoderPort;
import com.authmodule.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("CreateUserService 테스트")
@ExtendWith(SpringExtension.class)
class CreateUserServiceTest {
    @InjectMocks
    private CreateUserService createUserService;
    @Mock
    private CreateUserPort createUserPort;
    @Mock
    private PasswordEncoderPort passwordEncoderPort;
    @DisplayName("유저 생성")
    @Test
    public void createUserTest(){
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .email("zxc123@naver.com")
                .password("originPassword")
                .name("홍길동")
                .nickname("홍길동123")
                .build();
        User expectedUser = User.builder()
                .id(new User.UserId(1L))
                .email("zxc123@naver.com")
                .password("encodedPassword")
                .name("홍길동")
                .nickname("홍길동123")
                .build();
        when(passwordEncoderPort.encode(any())).thenReturn("encodedPassword");
        when(createUserPort.createUser(any(User.class))).thenReturn(expectedUser);

        // When
        createUserService.createUser(createUserCommand);
        // Then
        verify(createUserPort, times(1)).createUser(any(User.class));
        verify(passwordEncoderPort, times(1)).encode(createUserCommand.getPassword());

    }

}
