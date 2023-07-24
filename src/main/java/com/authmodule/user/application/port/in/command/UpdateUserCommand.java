package com.authmodule.user.application.port.in.command;

import com.authmodule.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UpdateUserCommand {
    @NotBlank
    private final User.UserId userId;
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
