package com.authmodule.common.advice;

import com.authmodule.common.ApiResponse;
import com.authmodule.common.ErrorApiResponse;
import com.authmodule.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleUserNotFoundException(UserNotFoundException ex) {
        return ErrorApiResponse.of(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ErrorApiResponse.of(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(UserBadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse handleUserBadCredentialsException(UserBadCredentialsException ex) {
        return ErrorApiResponse.of(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleValidationException(MethodArgumentNotValidException ex) {
        return ErrorApiResponse.of(HttpStatus.BAD_REQUEST.value(), ErrorMessage.INVALID_ARGUMENT.getMessage());
    }

    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleTokenException(TokenException ex) {
        return ErrorApiResponse.of(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleConstraintViolationException(ConstraintViolationException ex) {
        return ErrorApiResponse.of(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
