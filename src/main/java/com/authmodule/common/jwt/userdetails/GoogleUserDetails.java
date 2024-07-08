package com.authmodule.common.jwt.userdetails;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

@AllArgsConstructor
public class GoogleUserDetails implements OAuth2UserDetails {
    private final OAuth2User oAuth2User;
    @Override
    public String getProviderId() {
        return oAuth2User.getAttribute("sub");
    }

    @Override
    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("name");
    }

    @Override
    public String getNickname() {
        return oAuth2User.getAttribute("name"); // nickname 없음
    }

    @Override
    public String getProfileImageUrl() {
        return oAuth2User.getAttribute("picture");
    }
}
