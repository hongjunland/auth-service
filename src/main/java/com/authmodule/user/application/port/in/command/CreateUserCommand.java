package com.authmodule.user.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class CreateUserCommand {

    private String email;
    private String password;
    private String nickname;
    private String name;

}
