package com.test.current.account.application.exception;

public class AccountAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "Account with unique identifier %s already exists.";

    public AccountAlreadyExistsException(String uniqueIdentifier) {
        super(String.format(MESSAGE, uniqueIdentifier));
    }
}
