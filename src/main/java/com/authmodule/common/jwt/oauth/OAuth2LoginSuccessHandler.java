package com.authmodule.common.jwt.oauth;

import com.authmodule.common.config.properties.AppProperties;
import com.authmodule.common.jwt.JwtToken;
import com.authmodule.common.jwt.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtProvider JWtProvider;
    private final AppProperties appProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JwtToken jwtToken = JWtProvider.generateToken(authentication);
        String param = "?accessToken=%s&refreshToken=%s&expiration=%s".formatted(jwtToken.getAccessToken(), jwtToken.getRefreshToken(), jwtToken.getExpiration().toString());
        response.sendRedirect(appProperties.getOauth2().getAllowedRedirectUri() + param);
    }
}
