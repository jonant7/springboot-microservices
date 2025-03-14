package com.core.accountservice.account.domain.dtos;

import com.core.accountservice.shared.domain.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class AccountPatchDTO {

    private Status status;

}
