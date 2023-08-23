package com.authmodule.user.adapter.out.persistence;

import com.authmodule.common.exception.UserAlreadyExistsException;
import com.authmodule.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("UserPersistenceAdapterTest 테스트")
@ExtendWith(MockitoExtension.class)
class UserPersistenceAdapterTest{
    @Mock
    private SpringDataUserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserPersistenceAdapter userPersistenceAdapter;

    @DisplayName("유저등록시 이메일 중복 검사")
    @Test
    public void givenExistingEmail_whenCreateUser_thenThrowException() {
        User user = User.builder()
                .email("existing@email.com")
                .nickname("nickname")
                .build();

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(new UserJpaEntity()));

        assertThrows(UserAlreadyExistsException.class, () -> {
            userPersistenceAdapter.createUser(user);
        });

        verify(userRepository, times(1)).findByEmail(user.getEmail());
    }
    @DisplayName("유저등록시 닉네임 중복 검사")
    @Test
    public void givenExistingNickname_whenCreateUser_thenThrowException() {
        User user = User.builder()
                .email("existing@email.com")
                .nickname("nickname")
                .build();

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(userRepository.findByNickname(any())).thenReturn(Optional.of(new UserJpaEntity()));

        assertThrows(UserAlreadyExistsException.class, () -> {
            userPersistenceAdapter.createUser(user);
        });

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, times(1)).findByNickname(user.getNickname());
    }

    @DisplayName("유저등록 검사")
    @Test
    public void givenUser_whenCreateUser_thenUserIsCreated() {
        User user = User.withoutId("zxc@naver.com", "zxczxc","홍길동","홍길동123");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userMapper.mapToJpaEntity(user)).thenReturn(new UserJpaEntity());
        when(userRepository.save(any(UserJpaEntity.class))).thenReturn(new UserJpaEntity());
        when(userMapper.mapToDomainEntity(any(UserJpaEntity.class))).thenReturn(user);

        userPersistenceAdapter.createUser(user);

        verify(userRepository).save(any(UserJpaEntity.class));
    }

}