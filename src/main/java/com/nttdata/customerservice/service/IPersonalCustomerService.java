package com.nttdata.customerservice.service;

import com.nttdata.customerservice.dto.PersonalCustomerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonalCustomerService {

    Flux<PersonalCustomerDto> getAll();

    Mono<PersonalCustomerDto> getById(String id);

    Mono<PersonalCustomerDto> register(PersonalCustomerDto customerDto);

    Mono<PersonalCustomerDto> updateById(String id, PersonalCustomerDto customerDto);

    Mono<Void> deleteById(String id);

}
