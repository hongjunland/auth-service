package com.authmodule.user.application.service;

import com.authmodule.common.annotaion.UseCase;
import com.authmodule.user.application.port.in.GetUserQuery;
import com.authmodule.user.application.port.out.LoadUserPort;
import com.authmodule.user.adapter.in.web.response.UserResponse;
import com.authmodule.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@UseCase
@Transactional
class LoadUserService implements GetUserQuery {
    private final LoadUserPort loadUserPort;
    @Override
    public UserResponse getUser(Long userId) {
        User user = loadUserPort.loadById(userId);
        return UserResponse.builder()
                .id(user.getId().value())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .build();
    }
}
