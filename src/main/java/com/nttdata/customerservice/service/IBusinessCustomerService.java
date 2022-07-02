package com.nttdata.customerservice.service;

import com.nttdata.customerservice.dto.BusinessCustomerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBusinessCustomerService {

    Flux<BusinessCustomerDto> getAll();

    Mono<BusinessCustomerDto> getById(String id);

    Mono<BusinessCustomerDto> register(BusinessCustomerDto customerDto);

    Mono<BusinessCustomerDto> updateById(String id, BusinessCustomerDto customerDto);

    Mono<Void> deleteById(String id);

}
