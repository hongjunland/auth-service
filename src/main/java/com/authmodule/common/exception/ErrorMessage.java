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
    INVALID_ARGUMENT("형식에 맞지 않은 속성 값이 있습니다."),
    INVALID_TOKEN("유효하지 않은 토큰"),
    EXPIRED_TOKEN("만료된 토큰"),
    UNSUPPORTED_TOKEN("지원하지 않는 토큰"),
    EMPTY_CLAIMS("토큰 클레임이 비어 있음"),
    MALFORMED_TOKEN("형식에 맞지 않는 토큰"),
    SOCIAL_LOGIN_NOT_ALLOWED("소셜 로그인 계정은 이메일 로그인이 불가능합니다.");
    private final String message;
}
