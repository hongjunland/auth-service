package com.authmodule.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    USER_EMAIL_DUPLICATE("User with same email already exists"),
    USER_NICKNAME_DUPLICATE("User with same nickname already exists");

    private final String message;

//    ErrorMessage(String message) {
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return message;
//    }

}
