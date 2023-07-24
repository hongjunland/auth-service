package com.authmodule.common.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class UserBadCredentialsException extends BadCredentialsException {
    public UserBadCredentialsException() {
        super(ErrorMessage.INVALID_PASSWORD.getMessage());
    }
    public UserBadCredentialsException(String message) {
        super(message);
    }
}
