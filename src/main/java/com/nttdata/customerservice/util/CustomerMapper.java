package com.nttdata.customerservice.util;

import com.nttdata.customerservice.dto.BusinessCustomerDto;
import com.nttdata.customerservice.dto.PersonalCustomerDto;
import com.nttdata.customerservice.model.BusinessCustomer;
import com.nttdata.customerservice.model.PersonalCustomer;

public class CustomerMapper {

    private CustomerMapper() {
        throw new IllegalStateException(Constants.UTILITY_CLASS);
    }

    public static PersonalCustomer toModel(PersonalCustomerDto customerDto) {
        return PersonalCustomer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .dni(customerDto.getDni())
                .build();
    }

    public static PersonalCustomerDto toDto(PersonalCustomer customer) {
        return PersonalCustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dni(customer.getDni())
                .build();
    }

    public static BusinessCustomer toModel(BusinessCustomerDto customerDto) {
        return BusinessCustomer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .city(customerDto.getCity())
                .address(customerDto.getAddress())
                .build();
    }

    public static BusinessCustomerDto toDto(BusinessCustomer customer) {
        return BusinessCustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .city(customer.getCity())
                .address(customer.getAddress())
                .build();
    }

}
