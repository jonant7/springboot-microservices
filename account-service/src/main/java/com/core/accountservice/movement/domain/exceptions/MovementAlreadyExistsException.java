package com.core.accountservice.movement.domain.exceptions;

import com.core.accountservice.account.domain.AccountType;

public class MovementAlreadyExistsException extends RuntimeException {
    public MovementAlreadyExistsException(AccountType type, Integer number) {
        super(String.format("Movement %s - %d already exists", type, number));
    }
}
