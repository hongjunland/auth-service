package com.authmodule.user.adapter.in.web;

import com.authmodule.common.SuccessApiResponse;
import com.authmodule.common.annotaion.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hello")
class HelloController {
    @GetMapping
    public SuccessApiResponse hello(){
        return SuccessApiResponse.of(new String("hello World!!"));
    }
}
