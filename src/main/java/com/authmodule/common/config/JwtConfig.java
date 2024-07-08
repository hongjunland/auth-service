package com.authmodule.common.config;

import com.authmodule.common.config.properties.JwtProperties;
import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;


@Configuration
public class JwtConfig {

    @Bean
    public JwtDecoder jwtDecoder(JwtProperties tokenProperties){
        byte[] secretByteKey = Decoders.BASE64.decode(tokenProperties.getSecret());
        SecretKey secretKey = Keys.hmacShaKeyFor(secretByteKey);
        return NimbusJwtDecoder
                .withSecretKey(secretKey)
                .build();
    }
    @Bean
    public JwtEncoder jwtEncoder(JwtProperties tokenProperties){
        byte[] secretByteKey = Decoders.BASE64.decode(tokenProperties.getSecret());
        SecretKey secretKey = Keys.hmacShaKeyFor(secretByteKey);
        JWK jwk = new OctetSequenceKey.Builder(secretKey)
                .algorithm(Algorithm.parse("HS256"))
                .build();
        JWKSet jwkSet = new JWKSet(jwk);
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(jwkSet);
        return new NimbusJwtEncoder(jwkSource);
    }
}
