package com.authmodule.user.application.service;

import com.authmodule.common.annotaion.UseCase;
import com.authmodule.user.application.port.in.CreateUserUseCase;
import com.authmodule.user.application.port.in.GetUserQuery;
import com.authmodule.user.application.port.in.command.CreateUserCommand;
import com.authmodule.user.application.port.out.CreateUserPort;
import com.authmodule.user.adapter.in.web.dto.response.CreateUserResponse;
import com.authmodule.user.application.port.out.LoadUserPort;
import com.authmodule.user.application.port.out.PasswordEncoderPort;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class CreateUserService implements CreateUserUseCase {
    private final CreateUserPort createUserPort;
    private final LoadUserPort loadUserPort;
    private final PasswordEncoderPort encoderPort;

    @Override
    public CreateUserResponse createUser(CreateUserCommand command) {
        User user = User.builder()
                .email(command.getEmail())
                .nickname(command.getNickname())
                .name(command.getName())
                .password(encoderPort.encode(command.getPassword()))
                .build();

        User createdUser = createUserPort.createUser(user);

        return CreateUserResponse.builder()
                .id(createdUser.getId().getValue())
                .name(createdUser.getNickname())
                .email(createdUser.getEmail())
                .name(createdUser.getName())
                .password(createdUser.getPassword())
                .nickname(createdUser.getNickname())
                .build();
    }
}
