package com.authmodule.user.domain;

import com.authmodule.common.jwt.Role;
import lombok.*;

import java.util.Set;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class User {
    private final UserId id;
    private final String email;
    private final String password;
    private final String name;
    private final String nickname;
    private final Set<Role> roles;

    public static User withId(UserId id, String email, String password, String name, String nickname) {
        return new User(id, email, password, name, nickname, Set.of(Role.ROLE_USER));
    }
    public static User withoutId(String email, String password, String name, String nickname){
        return new User(null, email, password, name, nickname, Set.of(Role.ROLE_USER));
    }

    public record UserId(Long value) { }

}
