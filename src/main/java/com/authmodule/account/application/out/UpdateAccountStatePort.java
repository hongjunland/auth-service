package com.authmodule.account.application.out;
import com.authmodule.account.domain.Account;
public interface UpdateAccountStatePort {
    void updateActivities(Account account);
}
