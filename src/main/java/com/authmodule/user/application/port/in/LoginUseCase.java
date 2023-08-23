package com.authmodule.user.application.port.in;

import com.authmodule.user.application.port.in.command.LoginCommand;
import com.authmodule.user.adapter.in.web.response.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginCommand command);
}
