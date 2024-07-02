package com.authmodule.common.config;

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
public class SwaggerConfig {
    @Value("${jwt.header}")
    private String header;
    @Bean
    public OpenAPI openAPI() {
        SecurityRequirement securityRequirement =
                new SecurityRequirement().addList(header);
        Components components = new Components().addSecuritySchemes(header,
                new SecurityScheme()
                        .name(header)
                        .in(SecurityScheme.In.HEADER)
                        .type(SecurityScheme.Type.APIKEY));
        return new OpenAPI()
                .info(info())
                .addSecurityItem(securityRequirement)
                .components(components);

    }

    private Info info() {
        return new Info()
                .title("auto-module API")
                .description("auto-module API 문서")
                .version("1.0");
    }
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30)
//                .securityContexts(Collections.singletonList(securityContext())) // 추가
//                .securitySchemes(List.of(apiKey())) // 추가
//                .useDefaultResponseMessages(false)
//                .select()
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    // 추가
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build();
//    }
//
//    // 추가
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return List.of(new SecurityReference("Authorization", authorizationScopes));
//    }
//
//    // 추가
//    private ApiKey apiKey() {
//        return new ApiKey("Authorization", "Authorization", "header");
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("auto-module API")
//                .description("auto-module API 문서")
//                .version("1.0")
//                .build();
//    }
}
