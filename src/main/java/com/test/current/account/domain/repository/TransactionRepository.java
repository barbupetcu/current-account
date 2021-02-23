package com.test.current.account.domain.repository;

import com.test.current.account.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByPayerAccountIdAndCreatedDateAfter(Long accountId, LocalDateTime date);
    List<Transaction> findAllByPayeeAccountIdAndCreatedDateAfter(Long accountId, LocalDateTime date);

}
