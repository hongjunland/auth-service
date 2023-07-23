package com.authmodule.user.adapter.in.web;

import com.authmodule.common.SuccessApiResponse;
import com.authmodule.common.WebAdapter;
import com.authmodule.user.application.port.in.CreateUserCommand;
import com.authmodule.user.application.port.in.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController {
    private final CreateUserUseCase createUserUseCase;
    @PostMapping
    public SuccessApiResponse createUser(@RequestBody @Valid CreateUserCommand userCommand){
        return SuccessApiResponse.of(createUserUseCase.createUser(userCommand));
    }

}
