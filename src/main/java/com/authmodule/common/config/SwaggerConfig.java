package com.authmodule.common.config;

import com.authmodule.common.utils.TokenProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;



@OpenAPIDefinition
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private final TokenProperties tokenProperties;
    @Bean
    public OpenAPI openAPI() {
        SecurityRequirement securityRequirement =
                new SecurityRequirement().addList(tokenProperties.getHeader());
        Components components = new Components().addSecuritySchemes(tokenProperties.getHeader(),
                new SecurityScheme()
                        .name(tokenProperties.getHeader())
                        .in(SecurityScheme.In.HEADER)
                        .type(SecurityScheme.Type.APIKEY));
        return new OpenAPI()
                .info(info())
                .addSecurityItem(securityRequirement)
                .components(components);

    }

    private Info info() {
        return new Info()
                .title("auth-service API")
                .description("auth-service API 문서")
                .version("1.1");
    }
}
