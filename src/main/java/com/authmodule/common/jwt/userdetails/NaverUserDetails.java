package com.authmodule.common.jwt.userdetails;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@AllArgsConstructor
public class NaverUserDetails implements OAuth2UserDetails {
    private final OAuth2User oAuth2User;

    @Override
    public String getProviderId() {
        Map<String, Object> response = oAuth2User.getAttribute("response");
        return (String) response.get("id");
    }

    @Override
    public String getEmail() {
        Map<String, Object> response = oAuth2User.getAttribute("response");
        return (String) response.get("email");
    }

    @Override
    public String getName() {
        Map<String, Object> response = oAuth2User.getAttribute("response");
        return (String) response.get("name");
    }

    @Override
    public String getNickname() {
        Map<String, Object> response = oAuth2User.getAttribute("response");
        return (String) response.get("nickname");
    }

    @Override
    public String getProfileImageUrl() {
        Map<String, Object> response = oAuth2User.getAttribute("response");
        return (String) response.get("profile_image");
    }
}
