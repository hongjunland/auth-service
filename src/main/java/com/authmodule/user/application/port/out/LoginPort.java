package com.authmodule.user.application.port.out;

import com.authmodule.common.jwt.JwtToken;

public interface LoginPort {
    JwtToken login(String email, String password);
    void logout(Long userId);
}
