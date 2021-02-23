package com.test.current.account.application.exception;

public class AccountNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Account with id %d cannot be found.";

    public AccountNotFoundException(Long accountId) {
        super(String.format(MESSAGE, accountId));
    }
}
