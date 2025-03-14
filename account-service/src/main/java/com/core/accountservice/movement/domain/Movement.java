package com.core.accountservice.movement.domain;

import com.core.accountservice.account.domain.Account;
import com.core.accountservice.movement.domain.dtos.MovementPatchDTO;
import com.core.accountservice.movement.domain.dtos.MovementPutDTO;
import com.core.accountservice.shared.domain.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "movements")
public class Movement extends AuditableEntity {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    private MovementType type;

    @Column(name = "value", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_movements_account"))
    private Account account;


    public void update(MovementPutDTO dto) {
        this.type = dto.getType();
        this.value = dto.getValue();
    }

    public void edit(MovementPatchDTO dto) {
        this.type = dto.getType() != null ? dto.getType() : this.type;
        this.value = dto.getValue() != null ? dto.getValue() : this.value;
    }
}
