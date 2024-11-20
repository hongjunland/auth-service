package com.authmodule.common.jwt;

import com.authmodule.common.config.properties.JwtProperties;
import com.authmodule.common.exception.ErrorMessage;
import com.authmodule.common.exception.TokenException;
import com.authmodule.common.jwt.refresh.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Getter
    private final JwtProperties properties;
    private final JwtDecoder jwtDecoder;
    private final JwtEncoder jwtEncoder;

    public Long extractMemberIdFromToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new TokenException(ErrorMessage.INVALID_TOKEN.getMessage());
        }
        return Long.parseLong(authentication.getName());
    }


    //    로그인시 발행토큰
    public JwtToken generateToken(Authentication auth) {
        return JwtToken.builder()
                .grantToken(properties.getGranted())
                .accessToken(generateAccessToken(auth))
                .refreshToken(generateRefreshToken(auth))
                .expiration(Instant.now().plusMillis(properties.getExpiration().getAccess()))
                .build();
    }


    //    Access Token 발행
    public String generateAccessToken(Authentication auth) {
        Instant now = Instant.now();
        String authorities = getAuthorities(auth);
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256")
                .type("JWT")
                .build();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(properties.getIssuer())
                .issuedAt(now)
                .expiresAt(now.plusMillis(properties.getExpiration().getAccess()))
                .subject(auth.getName())
                .claim("auth", authorities)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters
                        .from(jwsHeader, claims))
                .getTokenValue();
    }

    // Refresh Token 발행
    public String generateRefreshToken(Authentication auth) {
        Instant now = Instant.now();
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256")
                .type("JWT")
                .build();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(properties.getIssuer())
                .issuedAt(now)
                .expiresAt(now.plusMillis(properties.getExpiration().getRefresh()))
                .subject(auth.getName())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters
                        .from(jwsHeader, claims))
                .getTokenValue();
    }

    public Authentication getAuthentication(String accessToken) {
        Jwt jwt = jwtDecoder.decode(accessToken);
        Map<String, Object> claims = jwt.getClaims();
        Optional.ofNullable(claims.get("auth"))
                .orElseThrow(() -> new TokenException(ErrorMessage.INVALID_TOKEN.getMessage()));
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new JwtAuthenticationToken(jwt, authorities);
    }

    public boolean isValidToken(String token) {
        try {
            jwtDecoder.decode(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error(ErrorMessage.INVALID_TOKEN.getMessage(), e);
            throw new TokenException(ErrorMessage.INVALID_TOKEN.getMessage());
        } catch (ExpiredJwtException e) {
            log.error(ErrorMessage.EXPIRED_TOKEN.getMessage(), e);
            throw new TokenException(ErrorMessage.EXPIRED_TOKEN.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error(ErrorMessage.UNSUPPORTED_TOKEN.getMessage(), e);
            throw new TokenException(ErrorMessage.UNSUPPORTED_TOKEN.getMessage());
        } catch (IllegalArgumentException e) {
            log.error(ErrorMessage.EMPTY_CLAIMS.getMessage(), e);
            throw new TokenException(ErrorMessage.EMPTY_CLAIMS.getMessage());
        } catch (JwtValidationException e){
            log.error(ErrorMessage.INVALID_TOKEN.getMessage(), e);
            throw new TokenException(e.getMessage());
        }
    }

    private String getAuthorities(Authentication auth) {
        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    //    로그인시 발행토큰
    public JwtToken reissueToken(Authentication auth) {
        return JwtToken.builder()
                .grantToken(properties.getGranted())
                .accessToken(generateAccessToken(auth))
                .expiration(Instant.now().plusMillis(properties.getExpiration().getAccess()))
                .build();
    }
    public String extractUserIdFromToken(String refreshToken){
        Jwt jwt = jwtDecoder.decode(refreshToken);
        // 권한 정보(auth)는 필요하지 않으므로 생략
        return Optional.ofNullable(jwt.getSubject())
                .orElseThrow(() -> new TokenException(ErrorMessage.INVALID_TOKEN.getMessage()));
    }

}