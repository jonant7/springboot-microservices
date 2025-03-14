package com.core.customerservice.person.domain;

import com.core.customerservice.person.domain.dtos.PersonPatchDTO;
import com.core.customerservice.person.domain.dtos.PersonPutDTO;
import com.core.customerservice.shared.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "persons")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Person extends AuditableEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "identification", nullable = false, length = 256)
    private String identification;

    @Column(name = "address", length = 256)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    private Integer age;

    public void update(PersonPutDTO dto) {
        this.name = dto.getName();
        this.gender = dto.getGender();
        this.dateOfBirth = dto.getDateOfBirth();
        this.identification = dto.getIdentification();
        this.address = dto.getAddress();
        this.phone = dto.getPhone();
    }

    public void edit(PersonPatchDTO dto) {
        this.name = Objects.nonNull(dto.getName()) ? dto.getName() : this.name;
        this.gender = Objects.nonNull(dto.getGender()) ? dto.getGender() : this.gender;
        this.dateOfBirth = Objects.nonNull(dto.getDateOfBirth()) ? dto.getDateOfBirth() : this.dateOfBirth;
        this.identification = Objects.nonNull(dto.getIdentification()) ? dto.getIdentification() : this.identification;
        this.address = Objects.nonNull(dto.getAddress()) ? dto.getAddress() : this.address;
        this.phone = Objects.nonNull(dto.getPhone()) ? dto.getPhone() : this.phone;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
