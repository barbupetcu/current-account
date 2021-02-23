package com.test.current.account.application.service;

import com.test.current.account.application.model.CreateAccountCommand;

public interface AccountService {

    Long createAccount(CreateAccountCommand command);
    void closeAccount(Long accountId);

}
