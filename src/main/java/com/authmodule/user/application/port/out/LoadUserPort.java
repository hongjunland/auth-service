package com.authmodule.user.application.port.out;

import com.authmodule.user.domain.User;

import java.util.Optional;

public interface LoadUserPort {
    User loadById(Long id);
    User loadByEmail(String email);
    User loadByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}
