package com.authmodule.account.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountJpaEntity {
    @Id
    @GeneratedValue
    private Long id;
}
