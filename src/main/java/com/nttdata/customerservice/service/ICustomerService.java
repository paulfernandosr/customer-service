package com.nttdata.customerservice.service;

import com.nttdata.customerservice.dto.BusinessCustomerDto;
import com.nttdata.customerservice.dto.CustomerDto;
import com.nttdata.customerservice.dto.PersonalCustomerDto;
import com.nttdata.customerservice.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {

    Flux<CustomerDto> getAll();

    Flux<ProductDto> getAllProductsById(String id);

    Mono<CustomerDto> getById(String id);

    Mono<CustomerDto> registerPersonalCustomer(PersonalCustomerDto customerDto);

    Mono<CustomerDto> registerBusinessCustomer(BusinessCustomerDto customerDto);

    Mono<CustomerDto> updateById(String id, CustomerDto customerDto);

    Mono<Void> deleteById(String id);

}
