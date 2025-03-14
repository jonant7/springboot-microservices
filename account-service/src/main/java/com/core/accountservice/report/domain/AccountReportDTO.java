package com.core.accountservice.report.domain;

import com.core.accountservice.account.domain.Account;
import com.core.accountservice.movement.domain.Movement;
import com.core.accountservice.movement.domain.dtos.MovementDTO;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AccountReportDTO {
    private final String accountNumber;
    private final BigDecimal balance;
    private final List<MovementDTO> movements;

    public AccountReportDTO(Account account, List<Movement> movements) {
        this.accountNumber = account.getNumber();
        this.balance = account.getBalance();
        this.movements = movements.stream().map(MovementDTO::new).collect(Collectors.toList());
    }
}
