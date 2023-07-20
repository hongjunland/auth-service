package com.authmodule.user.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class User {
    private final UserId id;
    private final String email;
    private final String password;
    private final String nickname;

    @Value
    public static class UserId {
        private final Long value;
    }
}
