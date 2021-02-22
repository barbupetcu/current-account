package com.test.current.account.api.controller;

import com.test.current.account.api.model.TimeUnit;
import com.test.current.account.api.model.TransactionsResource;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.test.current.account.api.ApiVersion.API_V1;

@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor
public class TransactionController {

    public static final String TRANSACTION = "/transaction";

    @Operation(summary = "Retrieve user's transactions.")
    @GetMapping(TRANSACTION)
    public TransactionsResource retrieveUserTransactions(@RequestParam TimeUnit unit,
                                                         @RequestParam Integer size) {
        return null;
    }


}