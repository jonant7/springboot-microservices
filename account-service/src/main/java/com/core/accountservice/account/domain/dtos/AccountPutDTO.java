package com.core.accountservice.account.domain.dtos;

import com.core.accountservice.account.domain.AccountType;
import com.core.accountservice.shared.domain.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter(AccessLevel.PRIVATE)
public class AccountPutDTO {

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
