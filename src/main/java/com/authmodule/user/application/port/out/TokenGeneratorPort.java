package com.authmodule.user.application.port.out;

import com.authmodule.common.jwt.JwtToken;
import org.springframework.security.core.Authentication;

public interface TokenGeneratorPort {
    //    로그인시 발행토큰
    JwtToken generateToken(Authentication auth);
}
