package com.core.accountservice.movement.domain.exceptions;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(BigDecimal balance) {
        super(String.format("Balance not available, current balance: %s", balance));
    }
}
