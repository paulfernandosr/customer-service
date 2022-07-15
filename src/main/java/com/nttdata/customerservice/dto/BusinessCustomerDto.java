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

    @NotNull(message = Constants.NOT_NULL)
    private final String name;

    @NotNull(message = Constants.NOT_NULL)
    private final String city;

    @NotNull(message = Constants.NOT_NULL)
    private final String address;

}
