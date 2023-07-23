package com.authmodule.user.application.port.out;

import com.authmodule.user.domain.User;

public interface CreateUserPort {
    User createUser(User user);
}
