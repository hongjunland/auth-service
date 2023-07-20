package com.authmodule.user.application.port.in;

public interface LoginUseCase {
    LoginResponse login(LoginCommand command);
}
