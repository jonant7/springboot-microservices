package com.core.customerservice.customer.domain.events;

import com.core.customerservice.account.domain.AccountPostDTO;
import com.core.customerservice.account.domain.AccountType;
import com.core.customerservice.shared.domain.Status;
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

    public CustomerCreatedEvent(AccountPostDTO dto) {
        this.number = dto.getNumber();
        this.type = dto.getType();
        this.openingBalance = dto.getOpeningBalance();
        this.status = dto.getStatus();
        this.customerId = dto.getCustomerId();
    }
}
