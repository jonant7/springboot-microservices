package com.core.accountservice.account.domain.exceptions;

import com.core.accountservice.account.domain.AccountType;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(AccountType type, String number) {
        super(String.format("Account %s - No. %s already exists", type, number));
    }
}
