package com.authmodule.account.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private Long depositor;
    private Long withdrawal;
    private Long accountId;
    private LocalDateTime dateTime;
    private Money money;
}
