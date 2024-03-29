package com.authmodule.user.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse implements Serializable{
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String name;
}
