package com.test.current.account.application.assembler;

import com.test.current.account.api.model.TransactionResource;
import com.test.current.account.api.model.TransactionType;
import com.test.current.account.domain.model.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionResourceAssembler {

    public static TransactionResource assemble(Transaction transaction, TransactionType transactionType) {
        return TransactionResource.builder()
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .occurredAt(transaction.getCreatedDate())
                .type(transactionType)
                .build();
    }
}
