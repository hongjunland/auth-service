package com.authmodule.account.application.in;

import com.authmodule.account.domain.Account.AccountId;
import com.authmodule.account.domain.Money;

public interface GetAccountBalanceQuery {
    Money getAccountBalance(AccountId accountId);
}
