package com.nttdata.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.customerservice.util.Constants;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessCustomerDto {

    private final String id;

    @NotNull
    private final String name;

    @NotNull
    private final String city;

    @NotNull
    private final String address;

}
