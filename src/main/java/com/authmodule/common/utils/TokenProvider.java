package com.authmodule.common.utils;

import ch.qos.logback.core.status.ErrorStatus;
import com.authmodule.common.exception.ErrorMessage;
import com.authmodule.common.exception.TokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider{
    private Key key;
    @Value("${jwt.expiration.access}")
    private Long accessTokenExp;
    @Value("${jwt.expiration.refresh}")
    private Long refreshTokenExp;

    public TokenProvider(@Value("${jwt.secret}") String secretKey){
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        this.key = Keys.hmacShaKeyFor(secretByteKey);
    }
    public Long extractMemberIdFromToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if (authentication == null || authentication.getName() == null) {
            throw new TokenException(ErrorMessage.INVALID_TOKEN.getMessage());
        }
        return Long.parseLong(authentication.getName());
    }

    //    로그인시 발행토큰
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
        Optional.ofNullable(claims.get("auth")).orElseThrow(() -> new TokenException(ErrorMessage.INVALID_TOKEN.getMessage()));
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
            log.error(ErrorMessage.INVALID_TOKEN.getMessage(), e);
            throw new TokenException(ErrorMessage.INVALID_TOKEN.getMessage());
        }catch (ExpiredJwtException e){
            log.error(ErrorMessage.EXPIRED_TOKEN.getMessage(), e);
            throw new TokenException(ErrorMessage.EXPIRED_TOKEN.getMessage());
        }catch (UnsupportedJwtException e){
            log.error(ErrorMessage.UNSUPPORTED_TOKEN.getMessage(), e);
            throw new TokenException(ErrorMessage.UNSUPPORTED_TOKEN.getMessage());
        }catch (IllegalArgumentException e){
            log.error(ErrorMessage.EMPTY_CLAIMS.getMessage(), e);
            throw new TokenException(ErrorMessage.EMPTY_CLAIMS.getMessage());
        }
    }

    public Claims parseClaims(String accessToken) throws ExpiredJwtException {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
    }


}