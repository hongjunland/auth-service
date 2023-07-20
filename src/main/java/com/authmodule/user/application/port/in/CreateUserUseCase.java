package com.authmodule.user.application.port.in;

public interface CreateUserUseCase {
    boolean createUser(CreateUserCommand command);
}
