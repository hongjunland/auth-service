package com.authmodule.user.adapter.out.persistence;

import com.authmodule.common.annotaion.PersistenceAdapter;
import com.authmodule.user.application.port.out.PasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


@PersistenceAdapter
@RequiredArgsConstructor
class PasswordEncoderAdapter implements PasswordEncoderPort {
    private final PasswordEncoder passwordEncoder;
    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String password, String encodedPassword)  {
        return Optional.of(passwordEncoder.matches(password, encodedPassword))
                .filter(matches -> matches)
                .orElse(false);
    }
}
