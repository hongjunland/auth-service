package com.authmodule.user.adapter.out.persistence;



import com.authmodule.common.exception.UserNotFoundException;
import com.authmodule.common.utils.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
@Transactional
class UserDetailsServiceImpl implements UserDetailsService {
    private final SpringDataUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username){
        UserJpaEntity user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return UserDetailsImpl.builder()
                .username(user.getId().toString())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}