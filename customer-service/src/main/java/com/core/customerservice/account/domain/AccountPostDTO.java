package com.core.customerservice.account.domain;

import com.core.customerservice.shared.domain.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountPostDTO {
    @NotNull
    private String number;
    @NotNull
    private AccountType type;
    @NotNull
    private BigDecimal openingBalance;
    @NotNull
    private Status status;
    private Long customerId;
}
