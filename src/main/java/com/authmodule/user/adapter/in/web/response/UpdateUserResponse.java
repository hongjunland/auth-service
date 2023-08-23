package com.authmodule.user.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UpdateUserResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final String nickname;
}