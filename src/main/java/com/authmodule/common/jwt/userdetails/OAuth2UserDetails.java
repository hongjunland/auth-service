package com.authmodule.common.jwt.userdetails;

public interface OAuth2UserDetails {
    String getProviderId();
    String getEmail();
    String getName();
    String getNickname();
    String getProfileImageUrl();
}
