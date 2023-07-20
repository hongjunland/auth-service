package com.authmodule.user.application.port.in;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class LoginCommand {
    @NotNull
    private String email;

    @NotNull
    private String password;
}
