package com.authmodule.user.adapter.in.web.response;

import lombok.Builder;


@Builder
public record CreateUserResponse(Long id, String name, String email, String password, String nickname) {
}
