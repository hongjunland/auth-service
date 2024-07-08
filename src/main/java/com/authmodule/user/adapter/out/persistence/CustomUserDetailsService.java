package com.authmodule.user.adapter.out.persistence;



import com.authmodule.common.exception.ErrorMessage;
import com.authmodule.common.exception.UserNotFoundException;
import com.authmodule.common.jwt.userdetails.CustomUserDetails;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@RequiredArgsConstructor
@Component
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final SpringDataUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username){
        UserJpaEntity user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
        if(user.getProvider() != null){
            throw new RuntimeException(ErrorMessage.SOCIAL_LOGIN_NOT_ALLOWED.getMessage());
        }
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map((authority)-> new SimpleGrantedAuthority(authority.name()))
                .toList();

        return CustomUserDetails.builder()
                .username(user.getId().toString())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}