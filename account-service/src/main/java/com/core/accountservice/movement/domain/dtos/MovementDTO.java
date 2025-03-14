package com.core.accountservice.movement.domain.dtos;

import com.core.accountservice.account.domain.dtos.AccountDTO;
import com.core.accountservice.movement.domain.Movement;
import com.core.accountservice.shared.domain.AbstractEntityDTO;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class MovementDTO extends AbstractEntityDTO {
    private final LocalDate date;
    private final String type;
    private final BigDecimal value;
    private final BigDecimal balance;
    private final AccountDTO account;

    public MovementDTO(Movement movement) {
        super(movement.getId());
        this.date = movement.getDate();
        this.type = movement.getType().name();
        this.value = movement.getValue();
        this.balance = movement.getBalance();
        this.account = new AccountDTO(movement.getAccount());
    }
}
