package com.core.accountservice.account.domain.dtos;

import com.core.accountservice.account.domain.AccountType;
import com.core.accountservice.customer.event.CustomerCreatedEvent;
import com.core.accountservice.shared.domain.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter(AccessLevel.PRIVATE)
public class AccountPostDTO {

    @NotNull
    private String number;
    @NotNull
    private AccountType type;
    @NotNull
    private BigDecimal openingBalance;
    @NotNull
    private Status status;
    @NotNull
    private Long customerId;

    public AccountPostDTO(CustomerCreatedEvent event) {
        this.number = event.getNumber();
        this.type = event.getType();
        this.openingBalance = event.getOpeningBalance();
        this.status = event.getStatus();
        this.customerId = event.getCustomerId();
    }
}
