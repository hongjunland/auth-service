package com.authmodule.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private Cors cors;
    private OAuth2 oauth2;

    @Data
    public static class Cors {
        private String allowedOrigins;
    }

    @Data
    public static class OAuth2 {
        private String allowedRedirectUri;
    }
}
