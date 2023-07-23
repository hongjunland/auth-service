package com.authmodule.user.application.port.in;

import com.authmodule.user.application.port.out.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserCommand command);
}
