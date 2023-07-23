package com.authmodule.user.application.port.in;

import com.authmodule.user.application.port.out.response.UserResponse;

public interface GetUserUseCase {
    UserResponse getUser(Long userId);
}
