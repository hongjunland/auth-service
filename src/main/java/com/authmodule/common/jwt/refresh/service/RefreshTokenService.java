package com.authmodule.common.jwt.refresh.service;

import com.authmodule.common.exception.TokenException;
import com.authmodule.common.exception.UserNotFoundException;
import com.authmodule.common.jwt.JwtProvider;
import com.authmodule.common.jwt.JwtToken;
import com.authmodule.common.jwt.Role;
import com.authmodule.common.jwt.refresh.repository.RefreshTokenRepository;
import com.authmodule.common.jwt.userdetails.CustomUserDetails;
import com.authmodule.user.adapter.in.web.reqeust.ReissueTokenRequest;
import com.authmodule.user.adapter.in.web.response.ReissueTokenResponse;
import com.authmodule.user.adapter.out.persistence.SpringDataUserRepository;
import com.authmodule.user.adapter.out.persistence.UserJpaEntity;
import com.authmodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final SpringDataUserRepository userRepository;


    @Transactional
    public ReissueTokenResponse reissue(ReissueTokenRequest reissueTokenRequest) {
        // 1. 리프레시 토큰 유효성 검사
        jwtProvider.isValidToken(reissueTokenRequest.refreshToken());
        String userId = jwtProvider.extractUserIdFromToken(reissueTokenRequest.refreshToken());
        if(!refreshTokenRepository.validateToken(userId, reissueTokenRequest.refreshToken())){
            throw new TokenException("해당 토큰이 유효하지 않습니다");
        }
        UserJpaEntity userJpaEntity =
                userRepository.findById(Long.parseLong(userId))
                        .orElseThrow(UserNotFoundException::new);
        List<SimpleGrantedAuthority> authorities = userJpaEntity.getRoles().stream()
                .map((authority)-> new SimpleGrantedAuthority(authority.name()))
                .toList();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, authorities);

        JwtToken newJwtToken = jwtProvider.generateToken(authentication);

        return ReissueTokenResponse.builder()
                .accessToken(newJwtToken.getAccessToken())
                .expiration(newJwtToken.getExpiration().toString())
                .build();
    }
}
