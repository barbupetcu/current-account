package com.test.current.account.application.service;

import com.test.current.account.application.exception.AccountAlreadyExistsException;
import com.test.current.account.application.exception.AccountNotFoundException;
import com.test.current.account.application.model.CreateAccountCommand;
import com.test.current.account.application.model.CreateAccountCommandBuilderTest;
import com.test.current.account.domain.model.Account;
import com.test.current.account.domain.model.AccountStatus;
import com.test.current.account.domain.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    private static final String UNIQUE_IDENTIFIER = "11223344";

    @Autowired
    private AccountRepository accountRepository;

    private AccountService accountService;

    @Before
    public void init() {
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    public void createAccount() {
        CreateAccountCommand command = CreateAccountCommandBuilderTest.aCommand(UNIQUE_IDENTIFIER);
        Long id = accountService.createAccount(command);

        Optional<Account> accountOptional = accountRepository.findById(id);

        assertTrue(accountOptional.isPresent());
        assertEquals(AccountStatus.ACTIVE, accountOptional.get().getStatus());
    }

    @Test(expected = AccountAlreadyExistsException.class)
    public void createAccountThrowsException() {
        CreateAccountCommand firstCommand = CreateAccountCommandBuilderTest.aCommand(UNIQUE_IDENTIFIER);
        accountService.createAccount(firstCommand);
        CreateAccountCommand secondCommand = CreateAccountCommandBuilderTest.aCommand(UNIQUE_IDENTIFIER);
        accountService.createAccount(secondCommand);
    }

    @Test
    public void closeAccount() {
        CreateAccountCommand command = CreateAccountCommandBuilderTest.aCommand(UNIQUE_IDENTIFIER);
        Long id = accountService.createAccount(command);

        accountService.closeAccount(id);
        Optional<Account> account = accountRepository.findById(id);

        assertTrue(account.isPresent());
        assertEquals(AccountStatus.CLOSED, account.get().getStatus());
    }

    @Test(expected = AccountNotFoundException.class)
    public void closeAccountThrowsException() {
        accountService.closeAccount(System.currentTimeMillis());
    }
}