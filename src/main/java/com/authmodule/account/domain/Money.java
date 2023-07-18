package com.authmodule.account.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Money {
    private int amount;
    private ActivityWindow activityWindow;

    public static Money add(Money baselineBalance, ActivityWindow activityWindow) {
        return new Money(baselineBalance.getAmount(), activityWindow);
    }

    public ActivityWindow negate() {
        return activityWindow;
    }

    public boolean isPositive() {
        return true;
    }
}
