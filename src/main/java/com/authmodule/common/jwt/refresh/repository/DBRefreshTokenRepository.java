package com.authmodule.common.jwt.refresh.repository;

import com.authmodule.common.jwt.refresh.entity.RefreshTokenJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
public class DBRefreshTokenRepository implements RefreshTokenRepository{
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    @Override
    public void saveToken(String userId, String refreshToken, Duration expiration) {
        Instant expirationTime = Instant.now().plus(expiration);
        RefreshTokenJpaEntity refreshTokenEntity = RefreshTokenJpaEntity.builder()
                .id(Long.valueOf(userId))
                .token(refreshToken)
                .expiration(expirationTime)
                .build();
        refreshTokenJpaRepository.save(refreshTokenEntity);
    }

    @Override
    public String getToken(String userId) {
        Optional<RefreshTokenJpaEntity> tokenEntity = refreshTokenJpaRepository.findById(Long.valueOf(userId));
        return tokenEntity.map(RefreshTokenJpaEntity::getToken).orElse(null);
    }

    @Override
    public void deleteToken(String userId) {
        refreshTokenJpaRepository.deleteById(Long.valueOf(userId));
    }

    @Override
    public boolean validateToken(String userId, String refreshToken) {
        Optional<RefreshTokenJpaEntity> tokenEntity = refreshTokenJpaRepository.findById(Long.valueOf(userId));
        if (tokenEntity.isPresent()) {
            RefreshTokenJpaEntity entity = tokenEntity.get();
            // 현재 시간이 만료 시간 이전이고, 토큰이 일치하는지 확인
            return entity.getExpiration().isAfter(Instant.now()) && entity.getToken().equals(refreshToken);
        }
        return false;
    }
}
