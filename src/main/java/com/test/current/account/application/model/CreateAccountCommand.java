package com.test.current.account.application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAccountCommand {
    private String name;
    private String uniqueIdentifier;
    private String email;
    private String phoneNumber;
    private String address;
}
