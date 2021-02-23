package com.test.current.account.domain.repository;

import com.test.current.account.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUniqueIdentifier(String uniqueIdentifier);
}
