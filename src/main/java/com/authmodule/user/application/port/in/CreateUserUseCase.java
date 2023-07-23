package com.authmodule.user.application.port.in;

import com.authmodule.user.application.port.in.command.CreateUserCommand;
import com.authmodule.user.application.port.out.response.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserCommand command);
}
