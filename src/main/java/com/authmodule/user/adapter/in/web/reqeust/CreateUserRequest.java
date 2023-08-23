package com.authmodule.user.adapter.in.web.reqeust;

import com.authmodule.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateUserRequest extends SelfValidating<CreateUserRequest> {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String name;

    public CreateUserRequest(String email, String password, String nickname, String name) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.validateSelf();
    }
}
