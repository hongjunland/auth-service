package com.authmodule.account.application.out;

import com.authmodule.account.domain.Account.AccountId;

public interface AccountLock {
    void lockAccount(AccountId accountId);
    void releaseAccount(AccountId accountId);
}
