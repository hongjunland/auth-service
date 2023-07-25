package com.authmodule.user.application.port.in.command;

import com.authmodule.user.adapter.in.web.dto.reqeust.UpdateUserRequest;
import com.authmodule.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class UpdateUserCommand {
    private final User.UserId userId;
    private final String email;
    private final String password;
    private final String nickname;
    private final String name;

}
