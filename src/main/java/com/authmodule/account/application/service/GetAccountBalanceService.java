package com.authmodule.account.application.service;

import com.authmodule.account.application.in.GetAccountBalanceQuery;
import com.authmodule.account.application.out.LoadAccountPort;
import com.authmodule.account.domain.Account;
import com.authmodule.account.domain.Money;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
class GetAccountBalanceService implements GetAccountBalanceQuery {
    private final LoadAccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(Account.AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now()).calculateBalance();
    }
}
