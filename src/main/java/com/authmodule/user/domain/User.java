package com.authmodule.user.domain;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class User {
    private final UserId id;
    private final String email;
    private final String password;
    private final String name;
    private final String nickname;

    public static User withId(UserId id, String email, String password, String name, String nickname) {
        return new User(id, email, password, name, nickname);
    }
    public static User withoutId(String email, String password, String name, String nickname){
        return new User(null, email, password, name, nickname);
    }

    public record UserId(Long value) { }
}
