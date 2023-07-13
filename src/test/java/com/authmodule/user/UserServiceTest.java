package com.authmodule.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void test_findById() {
        String email = "zxc123@google.com";
        String password = "password@@";
        User user = User.builder()
                .id(1L)
                .email(email)
                .password(password)
                .build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        User searchedUser = userRepository.findByEmail(email).orElseThrow();
        assertEquals(user, searchedUser);
    }

    @Test
    void test_createUser() {
        String email = "zxc123@google.com";
        String password = "password@@";
        // Given
        User newUser = User.builder()
                .id(1L)
                .email(email)
                .password(password)
                .build();
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        // When
        User createdUser = userService.createUser(newUser);

        // Then
        verify(userRepository).save(newUser);
        assertEquals(email, createdUser.getEmail());
        assertEquals(password, createdUser.getPassword());
    }

}
