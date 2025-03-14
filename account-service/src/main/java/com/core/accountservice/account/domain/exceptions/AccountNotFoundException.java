package com.core.accountservice.account.domain.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super(String.format("Account with id %s not found", id));
    }

    public AccountNotFoundException(String number) {
        super(String.format("Account with number %s not found", number));
    }
}
