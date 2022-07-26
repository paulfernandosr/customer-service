package com.nttdata.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.customerservice.util.Constants;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalCustomerDto {

    private final String id;

    @NotNull
    private final String firstName;

    @NotNull
    private final String lastName;

    @NotNull
    private final String dni;

}
