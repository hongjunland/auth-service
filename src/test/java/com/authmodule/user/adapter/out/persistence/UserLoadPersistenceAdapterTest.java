package com.authmodule.user.adapter.out.persistence;
import com.authmodule.common.exception.UserNotFoundException;
import com.authmodule.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("유저관련 조회 어댑터 테스트")
@ExtendWith(MockitoExtension.class)
class UserLoadPersistenceAdapterTest {
    @Mock
    private SpringDataUserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserLoadPersistenceAdapter userLoadPersistenceAdapter;

    private User user;
    private UserJpaEntity userJpaEntity;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(new User.UserId(1L))
                .email("zxc@naver.com")
                .name("홍길동")
                .nickname("홍길동123")
                .build();
        userJpaEntity = UserJpaEntity.builder()
                .id(1L)
                .email("zxc@naver.com")
                .nickname("홍길동123")
                .build();

    }

    @DisplayName("사용자를 찾을 수 없을 때 예외 발생")
    @Test
    public void givenUserNotfound_whenLoadUser_thenThrowException() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        assertThrows(UserNotFoundException.class, () -> {
            userLoadPersistenceAdapter.loadById(1L);
        });
        // then
        verify(userRepository, times(1)).findById(user.getId().getValue());
    }

    @DisplayName("아이디로 사용자 로드 성공")
    @Test
    public void givenUser_whenLoadUserById_thenUserIsCreated() {
        // Given
        Long id = 1L;

        // Setting up the behavior of the Mock objects
        when(userRepository.findById(id)).thenReturn(Optional.of(userJpaEntity));
        when(userMapper.mapToDomainEntity(userJpaEntity)).thenReturn(user);

        // When
        User result = userLoadPersistenceAdapter.loadById(id);

        // Then
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(id);
        verify(userMapper, times(1)).mapToDomainEntity(userJpaEntity);
    }

    @DisplayName("이메일로 사용자 로드 성공")
    @Test
    public void givenUser_whenLoadUserByEmail_thenUserIsCreated() {
        String email = "zxc@naver.com";
        // Given
        // Setting up the behavior of the Mock objects
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userJpaEntity));
        when(userMapper.mapToDomainEntity(userJpaEntity)).thenReturn(user);

        // When
        User result = userLoadPersistenceAdapter.loadByEmail(email);

        // Then
        assertEquals(email, result.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
        verify(userMapper, times(1)).mapToDomainEntity(userJpaEntity);
    }
    @DisplayName("닉네임으로 사용자 로드 성공")
    @Test
    public void givenUser_whenLoadUserByNickname_thenUserIsCreated() {
        // Given
        // Setting up the behavior of the Mock objects
        when(userRepository.findByNickname(userJpaEntity.getNickname())).thenReturn(Optional.of(userJpaEntity));
        when(userMapper.mapToDomainEntity(userJpaEntity)).thenReturn(user);

        // When
        User result = userLoadPersistenceAdapter.loadByNickname(userJpaEntity.getNickname());

        // Then
        assertEquals(userJpaEntity.getNickname(), result.getNickname());
        verify(userRepository, times(1)).findByNickname(userJpaEntity.getNickname());
        verify(userMapper, times(1)).mapToDomainEntity(userJpaEntity);
    }
    @DisplayName("유저 아이디로 사용자 여부 성공 테스트")
    @Test
    public void givenTrue_whenUserExistById_thenUserIsCreated() {
        // given
        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(true);

        boolean expectedTrue = userLoadPersistenceAdapter.existsById(id);
        boolean expectedFalse = userLoadPersistenceAdapter.existsById(2L);

        assertEquals(true, expectedTrue);
        assertEquals(false, expectedFalse);
        verify(userRepository, times(1)).existsById(id);
    }

    @DisplayName("이메일로 사용자 여부 성공 테스트")
    @Test
    public void givenTrue_whenUserExistByEmail_thenUserIsCreated() {
        // given
        String email = "zxc123@naver.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        boolean expectedTrue = userLoadPersistenceAdapter.existsByEmail(email);
        boolean expectedFalse = userLoadPersistenceAdapter.existsByEmail("zxczxc55@nate.com");

        assertEquals(true, expectedTrue);
        assertEquals(false, expectedFalse);
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @DisplayName("닉네임으로 사용자 여부 성공 테스트")
    @Test
    public void givenTrue_whenUserExistByNickname_thenUserIsCreated() {
        // given
        String nickname = "zxc";
        when(userRepository.existsByNickname(nickname)).thenReturn(true);

        boolean expectedTrue = userLoadPersistenceAdapter.existsByNickname(nickname);
        boolean expectedFalse = userLoadPersistenceAdapter.existsByNickname("zxczxc");

        assertEquals(true, expectedTrue);
        assertEquals(false, expectedFalse);
        verify(userRepository, times(1)).existsByNickname(nickname);
    }

//    @DisplayName("사용자 목록 검색")
//    @Test
//    public void searchUserList_test() {
//        //given
//        List<UserJpaEntity> userJpaEntityList = new ArrayList<>();
//        userJpaEntityList.add(new UserJpaEntity(1L, "zxczxc55@nate.com", "", "홍길동", 4));
//        userJpaEntityList.add(new UserJpaEntity(2L, "zxczxc45@nate.com", "", "홍동길",4));
//        userJpaEntityList.add(new UserJpaEntity(3L, "zxczxc35@nate.com", "", "김길동",3));
//        userJpaEntityList.add(new UserJpaEntity(4L, "zxczxc25@nate.com", "", "박길동",4));
//        when(userRepository.findAll()).thenReturn(userJpaEntityList);
//        when(userMapper.mapToDomainEntity(any())).thenReturn(user);
//
//        //when
//        List<User> userList = userLoadPersistenceAdapter.searchUserList();
//
//        //then
//        verify(userMapper, times(4)).mapToDomainEntity(any());
//        verify(userRepository, times(1)).findAll();
//        assertEquals(userList.size(), userJpaEntityList.size());
//    }



}
