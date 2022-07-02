package com.nttdata.customerservice.dto;

import com.nttdata.customerservice.util.Constants;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder(toBuilder = true)
public class BusinessCustomerDto {

    private final String id;

    @NotNull(message = Constants.NOT_NULL)
    private final String name;

    @NotNull(message = Constants.NOT_NULL)
    private final String address;

    @NotNull(message = Constants.NOT_NULL)
    private final String city;

}
