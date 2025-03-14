package com.core.accountservice.account.domain.dtos;

import com.core.accountservice.account.domain.Account;
import com.core.accountservice.shared.domain.AbstractEntityDTO;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountDTO extends AbstractEntityDTO {
    private final String number;
    private final String type;
    private final BigDecimal balance;
    private final Boolean status;
    private final Long customerId;

    public AccountDTO(Account account) {
        super(account.getId());
        this.number = account.getNumber();
        this.type = account.getAccountType().name();
        this.balance = account.getBalance();
        this.status = account.getStatus().getValue();
        this.customerId = account.getCustomerId();
    }
}
