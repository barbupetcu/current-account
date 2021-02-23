package com.test.current.account.application.service;

import com.test.current.account.api.model.TransactionResource;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    List<TransactionResource> getAllTransactions(Long accountId, LocalDateTime occurredAfter);
}
