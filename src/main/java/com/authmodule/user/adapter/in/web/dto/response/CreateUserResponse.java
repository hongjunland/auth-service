package com.authmodule.user.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Builder
public class CreateUserResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final String nickname;
}
