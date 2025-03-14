package com.core.accountservice.customer.event;

import com.core.accountservice.account.domain.AccountType;
import com.core.accountservice.shared.domain.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CustomerCreatedEvent {
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
}
