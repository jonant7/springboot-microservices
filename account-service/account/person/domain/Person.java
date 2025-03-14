package com.core.customerservice.person.domain;

import com.core.customerservice.shared.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "persons")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Person extends AuditableEntity {

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="gender", nullable = false)
    private Gender gender;

    @Column(name="date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name="identification", nullable = false, length = 256)
    private String identification;

    @Column(name="address", length = 256)
    private String address;

    @Column(name="phone", length = 20)
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

    public Integer getAge() {
        LocalDate birthDate = this.dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
