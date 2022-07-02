package com.nttdata.customerservice.dto;

import com.nttdata.customerservice.util.Constants;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder(toBuilder = true)
public class PersonalCustomerDto {

    private final String id;

    @NotNull(message = Constants.NOT_NULL)
    private final String firstName;

    @NotNull(message = Constants.NOT_NULL)
    private final String lastName;

    @NotNull(message = Constants.NOT_NULL)
    private final String dni;

}
