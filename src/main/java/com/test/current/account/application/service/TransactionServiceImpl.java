package com.test.current.account.application.service;

import com.test.current.account.api.model.TransactionResource;
import com.test.current.account.api.model.TransactionType;
import com.test.current.account.application.assembler.TransactionResourceAssembler;
import com.test.current.account.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionResource> getAllTransactions(Long accountId, LocalDateTime occurredAfter) {
        return Stream.concat(getCreditTransactions(accountId, occurredAfter),
                getDebitTransactions(accountId, occurredAfter))
                .collect(Collectors.toList());
    }

    private Stream<TransactionResource> getCreditTransactions(Long accountId, LocalDateTime occurredAfter) {
        return transactionRepository.findAllByPayerAccountIdAndCreatedDateAfter(accountId, occurredAfter)
                .stream()
                .map(t -> TransactionResourceAssembler.assemble(t, TransactionType.CREDIT));
    }

    private Stream<TransactionResource> getDebitTransactions(Long accountId, LocalDateTime occurredAfter) {
        return transactionRepository.findAllByPayeeAccountIdAndCreatedDateAfter(accountId, occurredAfter)
                .stream()
                .map(t -> TransactionResourceAssembler.assemble(t, TransactionType.DEBIT));
    }


}
