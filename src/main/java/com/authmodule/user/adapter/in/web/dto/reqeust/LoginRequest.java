package com.authmodule.user.adapter.in.web.dto.reqeust;

import com.authmodule.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class LoginRequest extends SelfValidating<LoginRequest> {
    @Email
    private final String email;
    @NotBlank
    private final String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
        this.validateSelf();
    }
}
