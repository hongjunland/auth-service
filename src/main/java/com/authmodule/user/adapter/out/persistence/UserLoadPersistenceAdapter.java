package com.authmodule.user.adapter.out.persistence;

import com.authmodule.common.annotaion.PersistenceAdapter;
import com.authmodule.common.exception.ErrorMessage;
import com.authmodule.common.exception.UserNotFoundException;
import com.authmodule.common.utils.Token;
import com.authmodule.common.utils.TokenProvider;
import com.authmodule.user.application.port.out.LoadUserPort;
import com.authmodule.user.application.port.out.TokenGeneratorPort;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
@PersistenceAdapter
class UserLoadPersistenceAdapter implements LoadUserPort, TokenGeneratorPort {
    private final SpringDataUserRepository userRepository;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;

    @Override
    public User loadById(Long id) {
        UserJpaEntity userJpaEntity =
                userRepository.findById(id)
                        .orElseThrow(()-> new UserNotFoundException(ErrorMessage.USER_NOTFOUND.getMessage()));
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public User loadByEmail(String email) {
        UserJpaEntity userJpaEntity =
                userRepository.findByEmail(email)
                        .orElseThrow(()-> new UserNotFoundException(ErrorMessage.USER_NOTFOUND.getMessage()));
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public User loadByNickname(String nickname) {
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

    @Override
    public Token generateToken(Authentication auth) {
        return tokenProvider.generateToken(auth);
    }
}
