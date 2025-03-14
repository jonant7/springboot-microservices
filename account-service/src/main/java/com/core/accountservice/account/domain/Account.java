package com.core.accountservice.account.domain;

import com.core.accountservice.account.domain.dtos.AccountPatchDTO;
import com.core.accountservice.account.domain.dtos.AccountPutDTO;
import com.core.accountservice.shared.domain.AuditableEntity;
import com.core.accountservice.shared.domain.Status;
import com.core.accountservice.shared.domain.StatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "accounts")
public class Account extends AuditableEntity {

    @Column(name = "number", nullable = false, unique = true, length = 20)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    private AccountType accountType;

    @Column(name = "opening_balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal openingBalance;

    @Convert(converter = StatusConverter.class)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    public void update(AccountPutDTO dto) {
        this.number = dto.getNumber();
        this.accountType = dto.getType();
        this.openingBalance = dto.getOpeningBalance();
        this.status = dto.getStatus();
    }

    public void edit(AccountPatchDTO dto) {
        this.status = Objects.nonNull(dto.getStatus()) ? dto.getStatus() : this.status;
    }
}
