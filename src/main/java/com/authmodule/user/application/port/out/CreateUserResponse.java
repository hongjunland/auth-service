package com.authmodule.user.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Builder
public class CreateUserResponse {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String nickname;
}
