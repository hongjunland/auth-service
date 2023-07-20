package com.authmodule.user.adapter.out.persistence;

import com.authmodule.common.PersistenceAdapter;
import com.authmodule.common.exception.ErrorMessage;
import com.authmodule.common.exception.UserAlreadyExistsException;
import com.authmodule.user.application.port.out.CreateUserPort;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class UserPersistenceAdapter implements CreateUserPort {
    private final SpringDataUserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(o -> {throw new UserAlreadyExistsException(ErrorMessage.USER_EMAIL_DUPLICATE.getMessage());});
        userRepository.findByNickname(user.getEmail())
                .ifPresent(o -> {throw new UserAlreadyExistsException(ErrorMessage.USER_NICKNAME_DUPLICATE.getMessage());});

        UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .build();

        userRepository.save(userJpaEntity);
    }
}
