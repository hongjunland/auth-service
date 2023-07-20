package com.authmodule.user.application.port.in;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class CreateUserCommand {
    @NotNull
    private String name;

    @NotNull
//    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

//    public CreateUserCommand(String name, String email, String password, String nickname) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.nickname = nickname;
//    }
}
