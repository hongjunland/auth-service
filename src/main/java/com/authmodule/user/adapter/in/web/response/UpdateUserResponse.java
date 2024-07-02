package com.authmodule.user.adapter.in.web.response;

import lombok.Builder;

@Builder
public record UpdateUserResponse(Long id, String name, String email, String password, String nickname) {
}