package com.authmodule.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class SuccessApiResponseWithData<T> extends ApiResponse{
    private final T data;
    private SuccessApiResponseWithData(T data){
        super(HttpStatus.OK.value(), "성공");
        this.data = data;
    }
    public static <T> SuccessApiResponseWithData<T> of(T data){
        return new SuccessApiResponseWithData<>(data);
    }
}
