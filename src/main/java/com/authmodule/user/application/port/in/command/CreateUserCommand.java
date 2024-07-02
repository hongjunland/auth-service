package com.authmodule.user.application.port.in.command;

import lombok.Builder;


@Builder
public record CreateUserCommand(String email, String password, String nickname, String name) {

}
