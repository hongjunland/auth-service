package com.authmodule.user.application.service;

import com.authmodule.common.annotaion.UseCase;
import com.authmodule.user.application.port.in.CreateUserUseCase;
import com.authmodule.user.application.port.in.command.CreateUserCommand;
import com.authmodule.user.application.port.out.CreateUserPort;
import com.authmodule.user.adapter.in.web.response.CreateUserResponse;
import com.authmodule.user.application.port.out.PasswordEncoderPort;
import com.authmodule.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@UseCase
@Transactional
class CreateUserService implements CreateUserUseCase {
    private final CreateUserPort createUserPort;
    private final PasswordEncoderPort encoderPort;

    @Override
    public CreateUserResponse createUser(CreateUserCommand command) {
        User user = User.builder()
                .email(command.email())
                .nickname(command.nickname())
                .name(command.name())
                .password(encoderPort.encode(command.password()))
                .build();

        User createdUser = createUserPort.createUser(user);

        return CreateUserResponse.builder()
                .id(createdUser.getId().value())
                .name(createdUser.getNickname())
                .email(createdUser.getEmail())
                .name(createdUser.getName())
                .password(createdUser.getPassword())
                .nickname(createdUser.getNickname())
                .build();
    }
}
