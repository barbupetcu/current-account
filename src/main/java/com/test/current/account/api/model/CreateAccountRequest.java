package com.test.current.account.api.model;

import com.test.current.account.infrastructure.commons.CustomJsonSerializable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CreateAccountRequest implements CustomJsonSerializable {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "uniqueIdentifier is required")
    private String uniqueIdentifier;

    @Pattern(regexp = ".+@.+\\..+", message = "email is not valid")
    private String email;

    private String phoneNumber;

    @NotBlank(message = "address is required")
    private String address;

}
