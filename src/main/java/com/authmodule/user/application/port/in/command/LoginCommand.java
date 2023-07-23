package com.authmodule.user.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class LoginCommand {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
