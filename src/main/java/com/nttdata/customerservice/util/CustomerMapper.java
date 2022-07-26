package com.nttdata.customerservice.util;

import com.nttdata.customerservice.dto.BusinessCustomerDto;
import com.nttdata.customerservice.dto.CustomerDto;
import com.nttdata.customerservice.dto.PersonalCustomerDto;
import com.nttdata.customerservice.model.Customer;

public class CustomerMapper {

    private CustomerMapper() {
        throw new IllegalStateException(Constants.UTILITY_CLASS);
    }

    public static Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .dni(customerDto.getDni())
                .name(customerDto.getName())
                .city(customerDto.getCity())
                .address(customerDto.getAddress())
                .type(customerDto.getType())
                .build();
    }

    public static Customer toCustomer(PersonalCustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .dni(customerDto.getDni())
                .build();
    }

    public static Customer toCustomer(BusinessCustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .city(customerDto.getCity())
                .address(customerDto.getAddress())
                .build();
    }

    public static CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dni(customer.getDni())
                .name(customer.getName())
                .city(customer.getCity())
                .address(customer.getAddress())
                .type(customer.getType())
                .build();
    }

}
