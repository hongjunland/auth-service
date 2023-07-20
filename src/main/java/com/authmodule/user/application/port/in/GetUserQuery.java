package com.authmodule.user.application.port.in;

import com.authmodule.user.domain.User;

public interface GetUserQuery {
    String getUserEmail(User.UserId userId);
}
