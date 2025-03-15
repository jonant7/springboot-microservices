package com.core.customerservice.customer.domain.dtos;

import com.core.customerservice.account.domain.AccountPostDTO;
import com.core.customerservice.person.domain.Gender;
import com.core.customerservice.person.domain.dtos.PersonPostDTO;
import com.core.customerservice.shared.domain.Status;
import com.core.customerservice.shared.utils.TimeUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class CustomerPostDTO {

    @NotNull
    private String password;
    @NotNull
    private Status status;
    @NotNull
    private String name;
    @NotNull
    private Gender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TimeUtils.DATE_FORMAT)
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private String identification;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    @NotEmpty
    private List<AccountPostDTO> accounts;

    public PersonPostDTO getPersonPostDTO() {
        return new PersonPostDTO(
                this.name,
                this.gender,
                this.dateOfBirth,
                this.identification,
                this.address,
                this.phone
        );
    }

}