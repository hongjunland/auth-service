package com.authmodule.user.adapter.out.persistence;

import com.authmodule.common.util.security.Token;
import com.authmodule.user.application.port.out.TokenGeneratorPort;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider implements TokenGeneratorPort {
    private Key key;
    @Value("${jwt.expiration.access}")
    private Long accessTokenExp;
    @Value("${jwt.expiration.refresh}")
    private Long refreshTokenExp;

    public TokenProvider(@Value("${jwt.secret}") String secretKey){
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        this.key = Keys.hmacShaKeyFor(secretByteKey);
    }

    //    로그인시 발행토큰
    @Override
    public Token generateToken(Authentication auth){
        return Token.builder()
                .grantToken("Bearer")
                .accessToken(generateAccessToken(auth))
                .refreshToken(generateRefreshToken())
                .expiration(Instant.now().plusMillis(accessTokenExp))
                .build();
    }

    //    Access Token 발행
    public String generateAccessToken(Authentication auth){
        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("auth", getAuthorities(auth))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExp))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    // Refresh Token 발행
    public String generateRefreshToken(){
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExp))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private String getAuthorities(Authentication auth){
        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    public Authentication getAuthentication(String accessToken){
        Claims claims = parseClaims(accessToken);
        log.info(claims.toString());
        Optional.ofNullable(claims.get("auth")).orElseThrow(() -> new RuntimeException("권한 정보가 없는 토큰입니다."));
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
    public boolean isValidToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (SecurityException | MalformedJwtException e){
            log.error("Invalid Token", e);
        }catch (ExpiredJwtException e){
            log.error("Expired Token", e);
        }catch (UnsupportedJwtException e){
            log.error("Unsupported Token", e);
        }catch (IllegalArgumentException e){
            log.error("token claims string is empty", e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) throws ExpiredJwtException {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
    }


}