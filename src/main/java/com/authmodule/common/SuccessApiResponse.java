package com.authmodule.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SuccessApiResponse<T> extends ApiResponse{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    // 데이터 있을 시
    private SuccessApiResponse(T data){
        super(HttpStatus.OK.value(), "성공");
        this.data = data;
    }

    // 데이터 없을 시
    private SuccessApiResponse(){
        super(HttpStatus.OK.value(), "성공");
    }
    public static <T> SuccessApiResponse<T> of(T data){
        return new SuccessApiResponse<>(data);
    }
    public static <T> SuccessApiResponse<T> of(){
        return new SuccessApiResponse<>();
    }
}
