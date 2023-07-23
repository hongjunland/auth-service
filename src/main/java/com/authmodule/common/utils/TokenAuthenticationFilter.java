package com.authmodule.common.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@AllArgsConstructor
public class TokenAuthenticationFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TokenAuthenticationFilter -> doFileter");
        String token = resolveToken((HttpServletRequest) request);
        if(token!=null && tokenProvider.isValidToken(token)){
            Authentication auth = tokenProvider.getAuthentication(token);
            System.out.println(auth.toString());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request,response);
    }

    private String resolveToken(HttpServletRequest request) {
        System.out.println("TokenAuthenticationFilter -> resolveToken");
        String bearerToken = request.getHeader("Authorization");
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer") ? bearerToken.substring(7) : null;
    }
}