package com.authmodule.account.application.service;

import com.authmodule.account.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MoneyTransferProperties {
    private Money maximumTransferThreshold = Money.of(1_00_00L);


}
