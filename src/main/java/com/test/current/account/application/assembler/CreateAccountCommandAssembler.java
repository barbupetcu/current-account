package com.test.current.account.application.assembler;

import com.test.current.account.api.model.CreateAccountRequest;
import com.test.current.account.application.model.CreateAccountCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAccountCommandAssembler {

    public static CreateAccountCommand assemble(CreateAccountRequest request) {
        return CreateAccountCommand.builder()
                .name(request.getName())
                .uniqueIdentifier(request.getUniqueIdentifier())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .build();
    }
}
