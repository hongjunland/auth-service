package com.authmodule.user.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoMessage implements Serializable {
    private String requestId;
    private Long userId;
    private String nickname;
}
