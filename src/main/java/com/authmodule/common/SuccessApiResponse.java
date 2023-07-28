package com.authmodule.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SuccessApiResponse extends ApiResponse{
    private SuccessApiResponse(){
        super(HttpStatus.OK.value(), "성공");
    }
    public static SuccessApiResponse of(){
        return new SuccessApiResponse();
    }
}
