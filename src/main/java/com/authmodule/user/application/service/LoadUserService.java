package com.authmodule.user.application.service;

import com.authmodule.common.annotaion.UseCase;
import com.authmodule.user.application.port.in.GetUserUseCase;
import com.authmodule.user.application.port.out.LoadUserPort;
import com.authmodule.user.application.port.out.response.UserResponse;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class LoadUserService implements GetUserUseCase {
    private final LoadUserPort loadUserPort;
    @Override
    public UserResponse getUser(Long userId) {
        User user = loadUserPort.loadById(userId);

        return UserResponse.builder()
                .id(user.getId().getValue())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .build();
    }
}
