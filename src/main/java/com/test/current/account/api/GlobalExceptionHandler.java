package com.test.current.account.api;

import com.test.current.account.api.model.ErrorResponse;
import com.test.current.account.application.exception.AccountAlreadyExistsException;
import com.test.current.account.application.exception.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    private ErrorResponse handleException(AccountAlreadyExistsException e) {
        return ErrorResponse.anErrorResponse(e.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private ErrorResponse handleException(AccountNotFoundException e) {
        return ErrorResponse.anErrorResponse(e.getMessage());
    }

}
