package com.authmodule.user.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class User {
    private final UserId id;
    private final String email;
    private final String password;
    private final String nickname;
    private final String name;

//    public Optional<UserId> getId(){
//        return Optional.ofNullable(this.id);
//    }
    @Value
    public static class UserId {
        private final Long value;
    }
}
