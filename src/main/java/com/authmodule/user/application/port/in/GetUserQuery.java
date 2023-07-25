package com.authmodule.user.application.port.in;

import com.authmodule.user.adapter.in.web.dto.response.UserResponse;

public interface GetUserQuery {
    UserResponse getUser(Long userId);
}
