package com.authmodule.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Account {
    private Long id;
    private Money baselineBalance;
    private ActivityWindow activityWindow;

    public Money calculateBalance(){
        return Money.add(baselineBalance, activityWindow);
    }
    public boolean withdraw(Money money, Long targetAccountId){
        if(!mayWithdraw(money)){ return false;}
        Activity withdrawal = new Activity(id, id, targetAccountId, LocalDateTime.now(), money);
        activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(calculateBalance(), money.negate()).isPositive();
    }

    public boolean deposit(Money money, Long sourceAccountId){
        Activity deposit = new Activity(id, sourceAccountId, id, LocalDateTime.now(), money);
        this.activityWindow.addActivity(deposit);
        return true;
    }
}
