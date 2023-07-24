package com.authmodule.user.application.port.in;

import com.authmodule.user.application.port.out.response.UserResponse;

public interface GetUserQuery {
    UserResponse getUser(Long userId);
}
