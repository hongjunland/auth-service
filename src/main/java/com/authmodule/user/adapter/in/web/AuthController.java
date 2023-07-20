package com.authmodule.user.adapter.in.web;

import com.authmodule.common.WebAdapter;
import com.authmodule.user.application.port.in.LoginCommand;
import com.authmodule.user.application.port.in.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
class AuthController {
    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCommand loginCommand){
        return ResponseEntity.ok(loginUseCase.login(loginCommand));
    }
}
