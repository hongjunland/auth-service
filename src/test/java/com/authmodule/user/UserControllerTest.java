package com.authmodule.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    @Test
    public void test_createUser(){
        RequestUserCreate requestUserCreate = RequestUserCreate.builder()
                .email("zxc123naver.com")
                .password("zxczxczxc")
                .nickname("mockckck")
                .build();
        userController.createUser(requestUserCreate);
    }

}
