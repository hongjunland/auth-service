package com.authmodule.common.jwt.refresh.repository;

import java.time.Duration;

public interface RefreshTokenRepository {
    void saveToken(String userId, String refreshToken, Duration expiration);
    String getToken(String userId);
    void deleteToken(String userId);
    boolean validateToken(String userId, String refreshToken);
}
