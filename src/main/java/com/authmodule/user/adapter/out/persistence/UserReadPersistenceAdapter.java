package com.authmodule.user.adapter.out.persistence;

import com.authmodule.common.PersistenceAdapter;
import com.authmodule.common.exception.ErrorMessage;
import com.authmodule.common.exception.UserAlreadyExistsException;
import com.authmodule.common.exception.UserNotFoundException;
import com.authmodule.user.application.port.out.ReadUserPort;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class UserReadPersistenceAdapter implements ReadUserPort {
    private final SpringDataUserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public User readByEmail(String email) {
        UserJpaEntity userJpaEntity =
                userRepository.findByEmail(email)
                        .orElseThrow(()-> new UserNotFoundException(ErrorMessage.USER_NOTFOUND.getMessage()));
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public User readByNickname(String nickname) {
        UserJpaEntity userJpaEntity =
                userRepository.findByNickname(nickname)
                        .orElseThrow(()-> new UserNotFoundException(ErrorMessage.USER_NOTFOUND.getMessage()));
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
