package com.authmodule.user.adapter.in.web;

import com.authmodule.common.SuccessApiResponse;
import com.authmodule.common.annotaion.WebAdapter;
import com.authmodule.user.adapter.in.web.dto.reqeust.LoginRequest;
import com.authmodule.user.application.port.in.command.LoginCommand;
import com.authmodule.user.application.port.in.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
class AuthController {
    private final LoginUseCase loginUseCase;
    @PostMapping("/login")
    public SuccessApiResponse login(@RequestBody LoginRequest loginRequest){
        LoginCommand loginCommand = LoginCommand.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();
        return SuccessApiResponse.of(loginUseCase.login(loginCommand));
    }
}
