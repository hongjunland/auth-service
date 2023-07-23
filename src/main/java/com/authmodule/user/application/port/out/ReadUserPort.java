package com.authmodule.user.application.port.out;

import com.authmodule.user.domain.User;

import java.util.Optional;

public interface ReadUserPort {
    User readByEmail(String email);
    User readByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}
