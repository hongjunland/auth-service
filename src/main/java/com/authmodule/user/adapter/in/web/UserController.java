package com.authmodule.user.adapter.in.web;

import com.authmodule.common.SuccessApiResponse;
import com.authmodule.common.annotaion.CurrentUser;
import com.authmodule.common.annotaion.WebAdapter;
import com.authmodule.user.adapter.in.web.dto.reqeust.CreateUserRequest;
import com.authmodule.user.adapter.in.web.dto.reqeust.UpdateUserRequest;
import com.authmodule.user.application.port.in.GetUserQuery;
import com.authmodule.user.application.port.in.command.CreateUserCommand;
import com.authmodule.user.application.port.in.CreateUserUseCase;
import com.authmodule.user.application.port.in.command.UpdateUserCommand;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController {
    private final CreateUserUseCase createUserUseCase;
//    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserQuery getUserQuery;
    @PostMapping
    public SuccessApiResponse createUser(@RequestBody CreateUserRequest createUserRequest){
        CreateUserCommand userCommand = CreateUserCommand.builder()
                .email(createUserRequest.getEmail())
                .name(createUserRequest.getName())
                .nickname(createUserRequest.getNickname())
                .password(createUserRequest.getPassword())
                .build();
        return SuccessApiResponse.of(createUserUseCase.createUser(userCommand));
    }
//    @PutMapping("/{userId}")
//    public SuccessApiResponse updateUser(@RequestBody UpdateUserRequest createUserRequest, @CurrentUser Long userId){
//        UpdateUserCommand userCommand = UpdateUserCommand.builder()
//                .userId(new User.UserId(userId))
//                .email(createUserRequest.getEmail())
//                .name(createUserRequest.getName())
//                .nickname(createUserRequest.getNickname())
//                .password(createUserRequest.getPassword())
//                .build();
//        return SuccessApiResponse.of(updateUserUseCase.updateUser(userCommand));
//    }
    @GetMapping
    public SuccessApiResponse getCurrentUser(@CurrentUser Long userId){
        return SuccessApiResponse.of(getUserQuery.getUser(userId));
    }
    @GetMapping("/{userId}")
    public SuccessApiResponse getUserById(@PathVariable Long userId){
        return SuccessApiResponse.of(getUserQuery.getUser(userId));
    }

}
