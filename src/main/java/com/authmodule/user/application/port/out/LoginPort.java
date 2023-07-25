package com.authmodule.user.application.port.out;

import com.authmodule.user.adapter.in.web.dto.response.LoginResponse;

public interface LoginPort {
    LoginResponse login();
}
