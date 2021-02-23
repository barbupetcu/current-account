package com.test.current.account.application.service;

import com.test.current.account.domain.model.Transaction;
import com.test.current.account.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionGeneratorService {

    private final TransactionRepository transactionRepository;

    public void generateRandomTransactions(Long accountId) {
        generateCreditTransactions(accountId);
        generateDebitTransactions(accountId);
    }

    public void generateCreditTransactions(Long accountId) {
        int transactionCount = getRandomInteger(1,10);
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i <= transactionCount; i++) {
            Transaction transaction = Transaction.builder()
                    .amount(BigDecimal.TEN.multiply(BigDecimal.valueOf(i)))
                    .description("Description for transaction " + i)
                    .payeeAccountId(accountId)
                    .createdDate(LocalDateTime.now().minusDays(i))
                    .build();
            transactions.add(transaction);
        }

        save(transactions);
    }

    public void generateDebitTransactions(Long accountId) {
        int transactionCount = getRandomInteger(1,10);
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i <= transactionCount; i++) {
            Transaction transaction = Transaction.builder()
                    .amount(BigDecimal.TEN.multiply(BigDecimal.valueOf(i)))
                    .description("Description for transaction " + i)
                    .payerAccountId(accountId)
                    .createdDate(LocalDateTime.now().minusHours(i))
                    .build();
            transactions.add(transaction);
        }

        save(transactions);
    }

    public void save(List<Transaction> transactions) {
        if (!transactions.isEmpty()) {
            transactionRepository.saveAll(transactions);
        }
    }

    public int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }


}
