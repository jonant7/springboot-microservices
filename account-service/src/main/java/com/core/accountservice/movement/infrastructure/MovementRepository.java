package com.core.accountservice.movement.infrastructure;

import com.core.accountservice.account.domain.Account;
import com.core.accountservice.movement.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findAllByAccountAndDateBetween(Account account, LocalDate dateAfter, LocalDate dateBefore);
}
