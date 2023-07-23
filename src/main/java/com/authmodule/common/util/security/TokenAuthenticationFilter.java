package com.authmodule.common.util.security;

import com.authmodule.user.adapter.out.persistence.TokenProvider;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest) request);
        if(token!=null && tokenProvider.isValidToken(token)){
            Authentication auth = tokenProvider.getAuthentication(token);
            System.out.println(auth.toString());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request,response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer") ? bearerToken.substring(7) : null;
    }
}