package com.authmodule.common.jwt.userdetails;

import com.authmodule.common.jwt.oauth.OAuth2Provider;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuth2UserDetailsFactory {
    public static OAuth2UserDetails getOAuth2UserDetails(String registrationId, OAuth2User oAuth2User) {
        OAuth2Provider provider = OAuth2Provider.fromRegistrationId(registrationId);
        return switch (provider) {
            case GOOGLE -> new GoogleUserDetails(oAuth2User);
            case KAKAO -> new KakaoUserDetails(oAuth2User);
            case NAVER -> new NaverUserDetails(oAuth2User);
        };
    }
}
