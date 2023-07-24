package com.authmodule.user.adapter.in.web;

import com.authmodule.common.SuccessApiResponse;
import com.authmodule.common.annotaion.CurrentUser;
import com.authmodule.common.annotaion.WebAdapter;
import com.authmodule.user.application.port.in.GetUserQuery;
import com.authmodule.user.application.port.in.command.CreateUserCommand;
import com.authmodule.user.application.port.in.CreateUserUseCase;
import com.authmodule.user.application.port.in.command.UpdateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final GetUserQuery getUserQuery;
    @PostMapping
    public SuccessApiResponse createUser(@RequestBody @Valid CreateUserCommand userCommand){
        return SuccessApiResponse.of(createUserUseCase.createUser(userCommand));
    }
    @GetMapping
    public SuccessApiResponse getCurrentUser(@CurrentUser Long userId){
        return SuccessApiResponse.of(getUserQuery.getUser(userId));
    }
    @GetMapping("/{userId}")
    public SuccessApiResponse getUserById(@PathVariable Long userId){
        return SuccessApiResponse.of(getUserQuery.getUser(userId));
    }

}
