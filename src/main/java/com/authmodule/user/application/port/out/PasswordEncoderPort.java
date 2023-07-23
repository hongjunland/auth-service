package com.authmodule.user.application.port.out;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
    boolean matches(String password, String encodedPassword);
}
