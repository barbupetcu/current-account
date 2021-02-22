package com.test.current.account.application.service;

import com.test.current.account.api.model.CreateAccountRequest;

public interface AccountService {

    Long createAccount(CreateAccountRequest createAccountRequest);
    void closeAccount(Long accountId);

}
