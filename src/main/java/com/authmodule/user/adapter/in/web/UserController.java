package com.authmodule.user.adapter.in.web;

import com.authmodule.common.WebAdapter;
import com.authmodule.user.application.port.in.CreateUserCommand;
import com.authmodule.user.application.port.in.CreateUserUseCase;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController {
    private final CreateUserUseCase createUserUseCase;
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserCommand userCommand){
        return ResponseEntity.ok(createUserUseCase.createUser(userCommand));
    }

}
