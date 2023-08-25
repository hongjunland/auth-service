package com.authmodule.user.adapter.in.web;

import com.authmodule.common.SuccessApiResponse;
import com.authmodule.user.adapter.in.web.reqeust.CreateUserRequest;
import com.authmodule.user.adapter.in.web.response.UserResponse;
import com.authmodule.user.application.port.in.CreateUserUseCase;
import com.authmodule.user.application.port.in.GetUserQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("UserControllerTest 테스트")
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private CreateUserUseCase createUserUseCase;
    @Mock
    private GetUserQuery getUserQuery;

    @DisplayName("유저 생성 테스트")
    @Test
    public void shouldCreateUser_whenPostRequest(){
        // Given
        CreateUserRequest userRequest = new CreateUserRequest("zxc123@naver.com", "비밀번호1@@", "홍길동","길동123");

        // When
        SuccessApiResponse response = userController.createUser(userRequest);
        // Then
        verify(createUserUseCase, times(1)).createUser(any());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    @DisplayName("유저 정보 단일 조회")
    @Test
    public void shouldGetUser_whenGetRequest(){
        // Given
        UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .email("zxc123@naver.com")
                .name("이름1")
                .build();
        when(getUserQuery.getUser(1L)).thenReturn(userResponse);

        //When
        SuccessApiResponse response = userController.getUserById(1L);

        //Then
        verify(getUserQuery, times(1)).getUser(1L);
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        UserResponse resultUserResponse = (UserResponse) response.getData();
        Assertions.assertEquals(resultUserResponse, userResponse);
        Assertions.assertEquals(resultUserResponse.getId(), userResponse.getId());
        Assertions.assertEquals(resultUserResponse.getName(), userResponse.getName());
        Assertions.assertEquals(resultUserResponse.getEmail(), userResponse.getEmail());

    }
    @DisplayName("현재 로그인된 내 정보 조회")
    @Test
    public void shouldGetCurrentUser_whenGetRequest(){
        // Given
        UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .email("zxc123@naver.com")
                .name("이름1")
                .build();
        when(getUserQuery.getUser(1L)).thenReturn(userResponse);

        // When
        SuccessApiResponse response = userController.getCurrentUser(1L);

        // Then
        verify(getUserQuery, times(1)).getUser(1L);
        assertEquals(userResponse.getId(), ((UserResponse) response.getData()).getId());
        assertEquals(userResponse, response.getData());

    }
//    @DisplayName("사용자 목록 조회")
//    @Test
//    public void shouldGetUserList_whenGetRequest(){
//        // Given
//        List<UserResponse> userResponseList = new ArrayList<>();
//        userResponseList.add(new UserResponse(1L, "zxc123@example.com", "홍길동",4));
//        userResponseList.add(new UserResponse(2L, "zxc1234@example.com", "홍동길",4));
//        userResponseList.add(new UserResponse(3L, "zxc12345@example.com", "홍동동",4));
//        userResponseList.add(new UserResponse(4L, "zxc12346@example.com", "홍길길",4));
//        userResponseList.add(new UserResponse(5L, "zxc1233@example.com", "홍기동",4));
//        when(getUserQuery.getUserList()).thenReturn(userResponseList);
//
//        // When
//        SuccessApiResponse response = userController.getUserList();
//
//        // Then
//        verify(getUserQuery, times(1)).getUserList();
//        assertEquals(((ArrayList<UserResponse>)response.getData()).size(), 5);
//
//    }
}
