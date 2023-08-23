package com.authmodule.user.adapter.in.web;

import com.authmodule.common.ApiResponse;
import com.authmodule.common.SuccessApiResponse;
import com.authmodule.common.annotaion.CurrentUser;
import com.authmodule.common.annotaion.WebAdapter;
import com.authmodule.user.adapter.in.web.reqeust.CreateUserRequest;
import com.authmodule.user.application.port.in.GetUserQuery;
import com.authmodule.user.application.port.in.command.CreateUserCommand;
import com.authmodule.user.application.port.in.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final GetUserQuery getUserQuery;
    @PostMapping
    public SuccessApiResponse createUser(@RequestBody CreateUserRequest createUserRequest){
        CreateUserCommand userCommand = CreateUserCommand.builder()
                .email(createUserRequest.getEmail())
                .name(createUserRequest.getName())
                .nickname(createUserRequest.getNickname())
                .password(createUserRequest.getPassword())
                .build();
        createUserUseCase.createUser(userCommand);
        return SuccessApiResponse.of();
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
