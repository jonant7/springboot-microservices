package com.core.accountservice.movement.domain.dtos;

import com.core.accountservice.movement.domain.MovementType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
public class MovementPatchDTO {

    private LocalDate date;
    private MovementType type;
    private BigDecimal value;
    private BigDecimal balance;

}