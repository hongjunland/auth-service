package com.authmodule.user.adapter.in.web.reqeust;

import com.authmodule.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Value;



@Value
@EqualsAndHashCode(callSuper = false)
public class LoginRequest extends SelfValidating<LoginRequest> {
    @Email
    private String email;
    @NotBlank
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
        this.validateSelf();
    }
}
