package com.authmodule.account.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Value
@RequiredArgsConstructor
@Getter
public class Activity {
    private ActivityId id;
    @NotNull
    private final Account.AccountId ownerAccountId;

    @NotNull
    private final Account.AccountId sourceAccountId;

    @NotNull
    private final Account.AccountId targetAccountId;

    @NotNull
    private final LocalDateTime timestamp;

    @NotNull
    private final Money money;

    public Activity(
            @NotNull Account.AccountId ownerAccountId,
            @NotNull Account.AccountId sourceAccountId,
            @NotNull Account.AccountId targetAccountId,
            @NotNull LocalDateTime timestamp,
            @NotNull Money money
    ) {
        this.id = null;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timestamp = timestamp;
        this.money = money;

    }

    @Value
    public static class ActivityId {
        private final Long value;
    }
}
