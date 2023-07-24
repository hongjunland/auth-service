package com.authmodule.common.advice;

import com.authmodule.common.ErrorApiResponse;
import com.authmodule.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorApiResponse handleUserNotFoundException(UserNotFoundException ex) {
        return ErrorApiResponse.of(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorApiResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ErrorApiResponse.of(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(UserBadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorApiResponse handleUserBadCredentialsException(UserBadCredentialsException ex) {
        return ErrorApiResponse.of(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorApiResponse handleValidationException(MethodArgumentNotValidException ex) {
        return ErrorApiResponse.of(HttpStatus.BAD_REQUEST.value(), ErrorMessage.INVALID_ARGUMENT.getMessage());
    }

}
