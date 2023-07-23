package com.authmodule.user.application.service;

import com.authmodule.common.UseCase;
import com.authmodule.user.application.port.in.CreateUserCommand;
import com.authmodule.user.application.port.in.CreateUserUseCase;
import com.authmodule.user.application.port.out.CreateUserPort;
import com.authmodule.user.application.port.out.CreateUserResponse;
import com.authmodule.user.application.port.out.PasswordEncoderPort;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class CreateUserService implements CreateUserUseCase {
    private final CreateUserPort createUserPort;
    private final PasswordEncoderPort encoderPort;

    @Override
    public CreateUserResponse createUser(CreateUserCommand command) {
        User user = User.builder()
                .email(command.getEmail())
                .nickname(command.getNickname())
                .password(encoderPort.encode(command.getPassword()))
                .build();

        User createdUser = createUserPort.createUser(user);

        return CreateUserResponse.builder()
                .id(createdUser.getId().getValue())
                .name(createdUser.getNickname())
                .email(createdUser.getEmail())
                .password(createdUser.getPassword())
                .nickname(createdUser.getNickname())
                .build();
    }
}
