package com.core.accountservice.account.infrastructure;

import com.core.accountservice.account.domain.Account;
import com.core.accountservice.account.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountTypeAndNumber(AccountType type, String number);

    Optional<Account> findByNumber(String number);

    List<Account> findAllByCustomerId(Long customerId);
}
