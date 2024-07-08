package com.authmodule.common.jwt;

import lombok.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtToken {
    private String grantToken;
    private String accessToken;
    private String refreshToken;
    private Instant expiration;
}