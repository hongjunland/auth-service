package com.authmodule.user.application.service;

import com.authmodule.common.UseCase;
import com.authmodule.user.application.port.in.LoginCommand;
import com.authmodule.user.application.port.in.LoginResponse;
import com.authmodule.user.application.port.in.LoginUseCase;
import com.authmodule.user.application.port.out.LoginPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class LoginService implements LoginUseCase, UserDetailsService {
    private final LoginPort loginPort;

    @Override
    public LoginResponse login(LoginCommand command) {
        loginPort.login();
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberService.findByEmail(username);
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        UserDetails user = new MemberDetail(member.getMemberId().toString(), member.getPassword(), authorities);
//        return user;
    }
}
