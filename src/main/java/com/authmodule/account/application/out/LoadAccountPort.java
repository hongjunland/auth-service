package com.authmodule.account.application.out;

import com.authmodule.account.domain.Account;

import java.time.LocalDateTime;

public interface LoadAccountPort {
    Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate);
}
