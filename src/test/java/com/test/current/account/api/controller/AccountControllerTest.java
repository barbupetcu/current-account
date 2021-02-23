package com.test.current.account.api.controller;

import com.test.current.account.api.GlobalExceptionHandler;
import com.test.current.account.application.exception.AccountAlreadyExistsException;
import com.test.current.account.application.exception.AccountNotFoundException;
import com.test.current.account.application.model.CreateAccountCommand;
import com.test.current.account.application.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.test.current.account.api.ApiVersion.API_V1;
import static com.test.current.account.api.controller.AccountController.ACCOUNT;
import static com.test.current.account.api.model.CreateAccountRequestBuilderTest.anAccountRequest;
import static com.test.current.account.api.model.CreateAccountRequestBuilderTest.anInvalidAccountRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class AccountControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void successfulCreateAccount() throws Exception {
        doReturn(1L).when(accountService).createAccount(any(CreateAccountCommand.class));

        mockMvc.perform(post(API_V1 + ACCOUNT)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(anAccountRequest()))
                .andExpect(status().isOk());

        verify(accountService).createAccount(any(CreateAccountCommand.class));
    }

    @Test
    public void accountAlreadyExists() throws Exception {
        doThrow(AccountAlreadyExistsException.class)
                .when(accountService).createAccount(any(CreateAccountCommand.class));

        mockMvc.perform(post(API_V1 + ACCOUNT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(anInvalidAccountRequest()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void invalidAccountRequest() throws Exception {
        doThrow(AccountAlreadyExistsException.class)
                .when(accountService).createAccount(any(CreateAccountCommand.class));

        mockMvc.perform(post(API_V1 + ACCOUNT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(anAccountRequest()))
                .andExpect(status().isConflict());
    }

    @Test
    public void accountNotFoundException() throws Exception {
        doThrow(AccountNotFoundException.class)
                .when(accountService).closeAccount(any(Long.class));

        String url = API_V1 + ACCOUNT + 1L + "/close";

        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(anAccountRequest()))
                .andExpect(status().isNotFound());
    }

}