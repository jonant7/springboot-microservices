package com.core.accountservice.movement.domain.dtos;

import com.core.accountservice.movement.domain.MovementType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
public class MovementPostDTO {

    @NotNull
    private LocalDate date;
    @NotNull
    private MovementType type;
    @NotNull
    private BigDecimal value;
    @NotNull
    private BigDecimal balance;

}
