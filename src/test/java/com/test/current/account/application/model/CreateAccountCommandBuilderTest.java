package com.test.current.account.application.model;

import static org.junit.Assert.*;

public class CreateAccountCommandBuilderTest {

    public static CreateAccountCommand aCommand(String uniqueIdentifier) {
        return CreateAccountCommand.builder()
                .name("Petcu Barbu")
                .address("address")
                .phoneNumber("0040760000000")
                .email("barbu.petcu@gmail.com")
                .uniqueIdentifier(uniqueIdentifier)
                .address("address")
                .build();
    }

}