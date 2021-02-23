package com.test.current.account.application.service;

import com.test.current.account.application.assembler.AccountAssembler;
import com.test.current.account.application.exception.AccountAlreadyExistsException;
import com.test.current.account.application.exception.AccountNotFoundException;
import com.test.current.account.application.model.CreateAccountCommand;
import com.test.current.account.domain.model.Account;
import com.test.current.account.domain.model.AccountStatus;
import com.test.current.account.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Long createAccount(CreateAccountCommand command) {

        accountRepository.findByUniqueIdentifier(command.getUniqueIdentifier())
                .ifPresent(a -> {
                    throw new AccountAlreadyExistsException(command.getUniqueIdentifier());
                });
        Account account = AccountAssembler.assemble(command);
        return accountRepository.save(account).getId();
    }

    @Override
    @Transactional
    public void closeAccount(Long accountId) {

        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setStatus(AccountStatus.CLOSED);
            accountRepository.save(account);
        } else {
            throw new AccountNotFoundException(accountId);
        }

    }
}
