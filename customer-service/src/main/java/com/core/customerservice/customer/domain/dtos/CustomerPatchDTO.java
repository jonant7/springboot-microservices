package com.core.customerservice.customer.domain.dtos;

import com.core.customerservice.person.domain.Gender;
import com.core.customerservice.person.domain.dtos.PersonPatchDTO;
import com.core.customerservice.shared.domain.Status;
import com.core.customerservice.shared.utils.TimeUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class CustomerPatchDTO {

    private String password;
    private Status status;
    private String name;
    private Gender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TimeUtils.DATE_FORMAT)
    private LocalDate dateOfBirth;
    private String identification;
    private String address;
    private String phone;

    public PersonPatchDTO getPersonPatchDTO() {
        return new PersonPatchDTO(
                this.name,
                this.gender,
                this.dateOfBirth,
                this.identification,
                this.address,
                this.phone
        );
    }

}
