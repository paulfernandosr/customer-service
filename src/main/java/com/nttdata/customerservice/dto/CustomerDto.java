package com.nttdata.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    private final String id;

    private final String firstName;
    private final String lastName;
    private final String dni;

    private final String name;
    private final String city;
    private final String address;

    private final String type;

}
