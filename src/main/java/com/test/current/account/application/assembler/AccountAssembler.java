package com.test.current.account.application.assembler;

import com.test.current.account.application.model.CreateAccountCommand;
import com.test.current.account.domain.model.Account;
import com.test.current.account.domain.model.AccountStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountAssembler {

    public static Account assemble(CreateAccountCommand command) {
        return Account.builder()
                .name(command.getName())
                .uniqueIdentifier(command.getUniqueIdentifier())
                .email(command.getEmail())
                .phoneNumber(command.getPhoneNumber())
                .address(command.getAddress())
                .status(AccountStatus.ACTIVE)
                .build();
    }
}
