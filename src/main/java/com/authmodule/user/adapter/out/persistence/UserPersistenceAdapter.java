package com.authmodule.user.adapter.out.persistence;

import com.authmodule.common.annotaion.PersistenceAdapter;
import com.authmodule.common.exception.ErrorMessage;
import com.authmodule.common.exception.UserAlreadyExistsException;
import com.authmodule.user.application.port.out.CreateUserPort;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class UserPersistenceAdapter implements CreateUserPort {
    private final SpringDataUserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public User createUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(o -> {throw new UserAlreadyExistsException();});
        userRepository.findByNickname(user.getNickname())
                .ifPresent(o -> {throw new UserAlreadyExistsException();});
        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
        return userMapper.mapToDomainEntity(userRepository.save(userJpaEntity));
    }
}
