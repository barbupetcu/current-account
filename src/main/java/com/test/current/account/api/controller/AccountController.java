package com.test.current.account.api.controller;

import com.test.current.account.api.model.CreateAccountRequest;
import com.test.current.account.api.model.CreateAccountResponse;
import com.test.current.account.application.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.test.current.account.api.ApiVersion.API_V1;

@Slf4j
@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor
public class AccountController {

    private static final String ACCOUNT = "/account";
    private static final String CLOSE_ACCOUNT = "/account/{accountId}/close";

    private final AccountService accountService;

    @Operation(summary = "Create new account operation.")
    @PostMapping(ACCOUNT)
    public CreateAccountResponse createAccount(@RequestBody @Validated CreateAccountRequest request) {

        Long accountId = accountService.createAccount(request);

        return CreateAccountResponse.aResponse(accountId);
    }

    @Operation(summary = "Close an account.")
    @PutMapping(CLOSE_ACCOUNT)
    public void closeAccount(@PathVariable Long accountId) {
        accountService.closeAccount(accountId);
    }
}
