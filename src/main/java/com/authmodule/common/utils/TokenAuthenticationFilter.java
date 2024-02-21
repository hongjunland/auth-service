package com.authmodule.common.utils;

import com.authmodule.common.exception.TokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest) request);
        try {
            if (token != null && tokenProvider.isValidToken(token)) {
                Authentication auth = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            chain.doFilter(request, response);
        } catch (RuntimeException e) {
            handleException((HttpServletResponse) response, e);
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer") ? bearerToken.substring(7) : null;
    }

    private void handleException(HttpServletResponse response, RuntimeException e) throws IOException {
        // 적절한 HTTP 상태 코드 설정
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 예를 들어, 401 상태 코드
        // 오류 메시지 전송
        response.getWriter().write("Authentication Error Message: " + e.getMessage());
    }
}