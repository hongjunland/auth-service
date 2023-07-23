package com.authmodule.user.application.port.out.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LoginResponse {
    private final String accessToken;
    private final String refreshToken;
    private final String expiration;
}
