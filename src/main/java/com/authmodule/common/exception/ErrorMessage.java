package com.authmodule.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    USER_DUPLICATE( "이미 존재하는 사용자 입니다."),
    USER_NOTFOUND( "해당 사용자가 존재하지 않습니다."),
    INVALID_PASSWORD("유효하지 않은 비밀번호입니다."),
    INVALID_ARGUMENT("형식에 맞지 않은 속성 값이 있습니다.");
    private final String message;
}
