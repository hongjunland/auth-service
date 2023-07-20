package com.authmodule.user.application.service;

import com.authmodule.common.UseCase;
import com.authmodule.user.application.port.in.CreateUserCommand;
import com.authmodule.user.application.port.in.CreateUserUseCase;
import com.authmodule.user.application.port.out.CreateUserPort;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class CreateUserService implements CreateUserUseCase {
    private final CreateUserPort createUserPort;
    @Override
    public boolean createUser(CreateUserCommand command) {
        User user = User.builder()
                .email(command.getEmail())
                .nickname(command.getNickname())
                .password(command.getPassword())
                .build();
        createUserPort.createUser(user);
        return true;
    }
}
