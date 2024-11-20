package com.authmodule.common.jwt.refresh.repository;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRefreshTokenRepository implements RefreshTokenRepository {
    private final ConcurrentHashMap<String, String> tokenStore = new ConcurrentHashMap<>();

    @Override
    public void saveToken(String userId, String refreshToken, Duration expiration) {
        tokenStore.put(userId, refreshToken);
    }

    @Override
    public String getToken(String userId) {
        return tokenStore.get(userId);
    }

    @Override
    public void deleteToken(String userId) {
        tokenStore.remove(userId);
    }

    @Override
    public boolean validateToken(String userId, String refreshToken) {
        String storedToken = getToken(userId);
        return storedToken != null && storedToken.equals(refreshToken);
    }
}
