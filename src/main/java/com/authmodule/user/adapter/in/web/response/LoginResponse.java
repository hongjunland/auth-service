package com.authmodule.user.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
public record LoginResponse(String accessToken, String refreshToken, String expiration) {
}
