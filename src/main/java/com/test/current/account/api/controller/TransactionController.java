package com.test.current.account.api.controller;

import com.test.current.account.api.model.TimeUnit;
import com.test.current.account.api.model.TransactionsResource;
import com.test.current.account.infrastructure.security.UserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.test.current.account.api.ApiVersion.API_V1;
import static com.test.current.account.infrastructure.OpenApi.AUTHORIZATION_HEADER;

@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor
public class TransactionController {

    public static final String TRANSACTION = "/transaction";

    @Operation(summary = "Retrieve user's transactions.", security = @SecurityRequirement(name = AUTHORIZATION_HEADER))
    @GetMapping(TRANSACTION)
    public TransactionsResource retrieveUserTransactions(@RequestParam TimeUnit unit,
                                                         @RequestParam Integer size,
                                                         @AuthenticationPrincipal UserDetails userDetails) {
        return null;
    }


}