package com.authmodule.common.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String granted;
    private String header;
    private String secret;
    private String issuer;
    private Expiration expiration;
    @Getter
    @Setter
    public static class Expiration{
        private long access;
        private long refresh;
    }
}
