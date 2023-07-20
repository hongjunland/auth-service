package com.authmodule.user.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private final String accessToken;
    private final String refreshToken;
    private final String expiration;
}
