package com.authmodule.user.adapter.out.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DisplayName("UserDetailsServiceImplTest 테스트")
@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Mock
    private SpringDataUserRepository userRepository;

    @DisplayName("로그인 성공")
    @Test
    public void login(){
        //Given
        String email = "test@test.com";
        UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                .id(1L)
                .name("홍길동")
                .password("encodedPassword")
                .email(email)
                .build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userJpaEntity));

        //When
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        //Then
        assertNotNull(userDetails);
        assertEquals(userDetails.getUsername(), Long.toString(1L));
        assertEquals(userDetails.getPassword(), "encodedPassword");
        assertEquals(userDetails.getAuthorities().size(), 1);
        verify(userRepository, times(1)).findByEmail(email);
    }
}