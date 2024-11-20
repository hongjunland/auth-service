package com.authmodule.common.jwt.refresh.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;

@RequiredArgsConstructor
public class RedisRefreshTokenRepository implements RefreshTokenRepository{
    private final StringRedisTemplate redisTemplate;
    private static final String REFRESH_TOKEN_PREFIX = "refresh_token:";
    @Override
    public void saveToken(String userId, String refreshToken, Duration expiration) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        // Redis에 "refresh_token:userId" 형태로 저장
        ops.set(REFRESH_TOKEN_PREFIX + userId, refreshToken, expiration);
    }

    @Override
    public String getToken(String userId) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        // 저장된 리프레시 토큰 조회
        return ops.get(REFRESH_TOKEN_PREFIX + userId);
    }

    @Override
    public void deleteToken(String userId) {
        // Redis에서 리프레시 토큰 삭제
        redisTemplate.delete(REFRESH_TOKEN_PREFIX + userId);
    }

    @Override
    public boolean validateToken(String userId, String refreshToken) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String storedToken = ops.get(REFRESH_TOKEN_PREFIX + userId);
        // 저장된 토큰과 주어진 토큰을 비교
        return storedToken != null && storedToken.equals(refreshToken);
    }
}
