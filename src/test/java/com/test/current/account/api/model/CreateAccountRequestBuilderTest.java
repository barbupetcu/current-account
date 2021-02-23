package com.test.current.account.api.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class CreateAccountRequestBuilderTest {

    public static String anAccountRequest() {
        CreateAccountRequest request = CreateAccountRequest.builder()
                .name("Petcu Barbu")
                .address("address")
                .phoneNumber("0040760000000")
                .email("barbu.petcu@gmail.com")
                .uniqueIdentifier("112233445566")
                .address("address")
                .build();
        return toJson(request);
    }

    public static String anInvalidAccountRequest() {
        CreateAccountRequest request = CreateAccountRequest.builder()
                .uniqueIdentifier("112233445566")
                .build();
        return toJson(request);
    }

    public static String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}