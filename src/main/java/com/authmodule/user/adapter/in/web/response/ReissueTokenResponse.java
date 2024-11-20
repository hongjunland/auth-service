package com.authmodule.user.adapter.in.web.response;

import lombok.Builder;

@Builder
public record ReissueTokenResponse(String accessToken, String expiration) {
}
