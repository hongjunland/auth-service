package com.authmodule.common.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class UserBadCredentialsException extends BadCredentialsException {
    public UserBadCredentialsException(String message) {
        super(message);
    }
}
