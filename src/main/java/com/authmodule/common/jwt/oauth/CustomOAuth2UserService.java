package com.authmodule.common.jwt.oauth;

import com.authmodule.common.jwt.Role;
import com.authmodule.common.jwt.userdetails.OAuth2UserDetails;
import com.authmodule.common.jwt.userdetails.OAuth2UserDetailsFactory;
import com.authmodule.user.adapter.out.persistence.SpringDataUserRepository;
import com.authmodule.user.adapter.out.persistence.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final SpringDataUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserDetails oAuth2UserDetails = OAuth2UserDetailsFactory.getOAuth2UserDetails(provider, oAuth2User);
        UserJpaEntity userJpaEntity = createOAuth2User(oAuth2UserDetails, provider);

        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        attributes.put("userId", userJpaEntity.getId()); // 사용자 ID를 추가

        Collection<? extends GrantedAuthority> authorities = userJpaEntity.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.name()))
                .toList();
        return new DefaultOAuth2User(authorities, attributes, "userId");
    }

    private UserJpaEntity createOAuth2User(OAuth2UserDetails oAuth2UserDetails, String provider) {
        return userRepository.findByProviderAndProviderId(provider, oAuth2UserDetails.getProviderId())
                .orElseGet(() -> userRepository.save(UserJpaEntity.builder()
                        .email(oAuth2UserDetails.getEmail())
                        .name(oAuth2UserDetails.getName())
                        .nickname(oAuth2UserDetails.getNickname())
                        .provider(provider)
                        .profileImageUrl(oAuth2UserDetails.getProfileImageUrl())
                        .providerId(oAuth2UserDetails.getProviderId())
                        .roles(Set.of(Role.ROLE_USER))  // 기본 역할 설정
                        .build())
                );
    }
}
