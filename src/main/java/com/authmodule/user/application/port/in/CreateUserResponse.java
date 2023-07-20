package com.authmodule.user.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class CreateUserResponse {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String nickname;
}
