package com.authmodule.common.utils;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrantedAuthorityImpl implements GrantedAuthority {
    private String authority;
    @Override
    public String getAuthority() {
        return authority;
    }
}
