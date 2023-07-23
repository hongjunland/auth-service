package com.authmodule.user.application.port.out;

import com.authmodule.common.utils.Token;
import org.springframework.security.core.Authentication;

public interface TokenGeneratorPort {
    //    로그인시 발행토큰
    Token generateToken(Authentication auth);
}
