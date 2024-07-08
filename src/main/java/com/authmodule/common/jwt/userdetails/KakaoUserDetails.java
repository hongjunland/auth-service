package com.authmodule.common.jwt.userdetails;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
public class KakaoUserDetails implements OAuth2UserDetails {
    private final OAuth2User oAuth2User;


    @Override
    public String getProviderId() {
        return Objects.requireNonNull(oAuth2User.getAttribute("id")).toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getName() {
        return null; // 이름 권한 없음
    }

    @Override
    public String getNickname() {
        Map<String, Object> properties = oAuth2User.getAttribute("properties");
        return (String) properties.get("nickname");
    }

    @Override
    public String getProfileImageUrl() {
        Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        return (String) profile.get("profile_image_url");
    }
}
