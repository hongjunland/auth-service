package com.authmodule.user.adapter.in.web.dto.reqeust;

import com.authmodule.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class UpdateUserRequest extends SelfValidating<UpdateUserRequest> {
    @Email
    private final String email;
    @NotBlank
    private final String password;
    @NotBlank
    private final String nickname;
    @NotBlank
    private final String name;

    public UpdateUserRequest(String email, String password, String nickname, String name) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.validateSelf();
    }
}
