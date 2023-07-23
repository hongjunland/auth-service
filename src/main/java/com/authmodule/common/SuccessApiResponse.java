package com.authmodule.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SuccessApiResponse<T> extends ApiResponse{
    private T data;
    private SuccessApiResponse(T data){
        super(HttpStatus.OK.value(), "성공");
        this.data = data;
    }
    public static <T> SuccessApiResponse<T> of(T data){
        return new SuccessApiResponse<>(data);
    }
}
