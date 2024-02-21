package com.authmodule.user.adapter.in.web.reqeust;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRequestMessage implements Serializable {
    private String requestId;
    private String token;
}
