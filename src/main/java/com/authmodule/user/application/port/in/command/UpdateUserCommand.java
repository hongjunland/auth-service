package com.authmodule.user.application.port.in.command;

import com.authmodule.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Builder
public record UpdateUserCommand(User.UserId userId, String email, String password, String nickname, String name) {
}
