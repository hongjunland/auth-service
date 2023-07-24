package com.authmodule.user.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class CreateUserCommand {

    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String password;
    @NotBlank
    private final String nickname;
    @NotBlank
    private final String name;

}
