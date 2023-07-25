package com.authmodule.user.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class LoginCommand {
    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
}
