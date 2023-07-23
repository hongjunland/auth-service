package com.authmodule.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorApiResponse extends ApiResponse{
    private ErrorApiResponse(int status, String errorMessage){
        super(status, errorMessage);
    }
    public static ErrorApiResponse of(int status, String errorMessage){
        return new ErrorApiResponse(status, errorMessage);
    }
}
