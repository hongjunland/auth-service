package com.authmodule.common.exception;

import java.io.IOException;

public class TokenException extends RuntimeException {
    public TokenException(String msg) {
        super(msg);
    }
}