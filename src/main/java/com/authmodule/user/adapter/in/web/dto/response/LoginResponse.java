package com.authmodule.user.adapter.in.web.dto.response;

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
