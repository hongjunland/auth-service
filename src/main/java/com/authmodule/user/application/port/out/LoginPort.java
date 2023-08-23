package com.authmodule.user.application.port.out;

import com.authmodule.common.utils.Token;

public interface LoginPort {
    Token login(String email, String password);
}
