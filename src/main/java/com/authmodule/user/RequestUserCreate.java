package com.authmodule.user;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class RequestUserCreate {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 1, max = 20)
    private String password;

    @NotNull
    @Size(min = 1, max = 20)
    private String nickname;
}
