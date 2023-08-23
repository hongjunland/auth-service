package com.authmodule.user.application.service;

import com.authmodule.user.adapter.in.web.response.UserResponse;
import com.authmodule.user.application.port.out.LoadUserPort;
import com.authmodule.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@DisplayName("LoadUserServiceTest 테스트")
@ExtendWith(SpringExtension.class)
class LoadUserServiceTest {
    @InjectMocks
    private LoadUserService loadUserService;
    @Mock
    private LoadUserPort loadUserPort;
    @DisplayName("유저 id 기반 단일조회 테스트")
    @Test
    public void getUser_test(){
        // Given
        Long userId = 1L;
        User user = User.withId(new User.UserId(1L),"zxc123@naver.com","dasdsa","홍길동","홍길동123");
        when(loadUserPort.loadById(userId)).thenReturn(user);

        // When
        UserResponse userResponse = loadUserService.getUser(userId);

        //then
        verify(loadUserPort, times(1)).loadById(1L);
        assertEquals(userId, userResponse.getId());
    }

//    @DisplayName("유저목록 검색 테스트")
//    @Test
//    public void getUserList_test(){
//        // Given
//        List<User> userList = Arrays.asList(
//                User.withId(new User.UserId(1L),"zxc123@naver.com","dasdsa","홍길동","홍길동123"),
//                User.withId(new User.UserId(2L),"zxc12@naver.com","dasdsa","김길동", "김길동123"),
//                User.withId(new User.UserId(3L),"zxc1243@naver.com","dasdsa","박길동","박길동123")
//        );
//        when(loadUserPort.se()).thenReturn(userList);
//
//        // When
//        List<UserResponse> userResponseList = loadUserService.getUserList();
//        // Then
//        verify(loadUserPort, times(1)).searchUserList();
//    }

}
