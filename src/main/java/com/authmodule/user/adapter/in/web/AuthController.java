package com.authmodule.user.adapter.in.web;

import com.authmodule.common.SuccessApiResponse;
import com.authmodule.common.annotaion.CurrentUser;
import com.authmodule.common.annotaion.WebAdapter;
import com.authmodule.common.jwt.refresh.service.RefreshTokenService;
import com.authmodule.user.adapter.in.web.reqeust.LoginRequest;
import com.authmodule.user.adapter.in.web.reqeust.ReissueTokenRequest;
import com.authmodule.user.application.port.in.command.LoginCommand;
import com.authmodule.user.application.port.in.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
class AuthController {
    private final LoginUseCase loginUseCase;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/login")
    public SuccessApiResponse<?> login(@RequestBody LoginRequest loginRequest){
        LoginCommand loginCommand = LoginCommand.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();
        return SuccessApiResponse.of(loginUseCase.login(loginCommand));
    }
    @PostMapping("/reissue")
    public SuccessApiResponse<?> reissue(@RequestBody ReissueTokenRequest reissueTokenRequest){
        return SuccessApiResponse.of(refreshTokenService.reissue(reissueTokenRequest));
    }
    @PostMapping("/logout")
    public SuccessApiResponse<Void> logout(@CurrentUser Long userId){
        loginUseCase.logout(userId);
        return SuccessApiResponse.of();
    }
}
