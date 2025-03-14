package com.core.customerservice.customer.domain;

import com.core.customerservice.customer.domain.dtos.CustomerPatchDTO;
import com.core.customerservice.customer.domain.dtos.CustomerPutDTO;
import com.core.customerservice.person.domain.Person;
import com.core.customerservice.shared.domain.AuditableEntity;
import com.core.customerservice.shared.domain.Status;
import com.core.customerservice.shared.domain.StatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "customers")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Customer extends AuditableEntity {

    @Column(name = "password", nullable = false)
    private String password;

    @Convert(converter = StatusConverter.class)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "person_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_customers_person"))
    private Person person;

    public void update(CustomerPutDTO dto) {
        this.password = dto.getPassword();
        this.status = dto.getStatus();
    }

    public void edit(CustomerPatchDTO dto) {
        this.password = Objects.nonNull(dto.getPassword()) ? dto.getPassword() : this.password;
        this.status = Objects.nonNull(dto.getStatus()) ? dto.getStatus() : this.status;
    }

}