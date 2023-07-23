package com.authmodule.user.application.port.in;

import com.authmodule.user.application.port.out.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginCommand command);
}
