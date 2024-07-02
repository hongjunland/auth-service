package com.authmodule.user.adapter.out.persistence;

import com.authmodule.common.annotaion.PersistenceAdapter;
import com.authmodule.common.exception.UserNotFoundException;
import com.authmodule.common.utils.TokenProvider;
import com.authmodule.user.application.port.out.LoadUserPort;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class UserLoadPersistenceAdapter implements LoadUserPort {
    private final SpringDataUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User loadById(Long id) {
        UserJpaEntity userJpaEntity =
                userRepository.findById(id)
                        .orElseThrow(UserNotFoundException::new);
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public User loadByEmail(String email) {
        UserJpaEntity userJpaEntity =
                userRepository.findByEmail(email)
                        .orElseThrow(UserNotFoundException::new);
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public User loadByNickname(String nickname) {
        UserJpaEntity userJpaEntity =
                userRepository.findByNickname(nickname)
                        .orElseThrow(UserNotFoundException::new);
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
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
