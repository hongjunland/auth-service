package com.authmodule.common.util.security;

import lombok.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Token {
    private String grantToken;
    private String accessToken;
    private String refreshToken;
    private Instant expiration;
}