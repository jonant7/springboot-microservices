package com.core.accountservice.account.domain.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super(String.format("Movement with id %s not found", id));
    }
}
