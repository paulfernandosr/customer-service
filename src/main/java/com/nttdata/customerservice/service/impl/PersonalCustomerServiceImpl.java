package com.nttdata.customerservice.service.impl;

import com.nttdata.customerservice.dto.PersonalCustomerDto;
import com.nttdata.customerservice.exception.CustomerNotFoundException;
import com.nttdata.customerservice.exception.DuplicateCustomerException;
import com.nttdata.customerservice.model.PersonalCustomer;
import com.nttdata.customerservice.repo.IPersonalCustomerRepo;
import com.nttdata.customerservice.service.IPersonalCustomerService;
import com.nttdata.customerservice.util.Constants;
import com.nttdata.customerservice.util.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonalCustomerServiceImpl implements IPersonalCustomerService {

    private final IPersonalCustomerRepo repo;

    @Override
    public Flux<PersonalCustomerDto> getAll() {
        return repo.findAll().map(CustomerMapper::toDto);
    }

    @Override
    public Mono<PersonalCustomerDto> getById(String id) {
        return repo.findById(id)
                .map(CustomerMapper::toDto)
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(String.format(Constants.CUSTOMER_NOT_FOUND, Constants.ID, id))));
    }

    @Override
    public Mono<PersonalCustomerDto> register(PersonalCustomerDto customerDto) {
        PersonalCustomer customer = CustomerMapper.toModel(customerDto);
        return repo.existsByDni(customer.getDni())
                .flatMap(exists -> (!exists)
                        ? repo.save(customer.toBuilder().id(null).build()).map(CustomerMapper::toDto)
                        : Mono.error(new DuplicateCustomerException(String.format(Constants.DUPLICATE_CUSTOMER, Constants.DNI, customer.getDni()))));
    }

    @Override
    public Mono<PersonalCustomerDto> updateById(String id, PersonalCustomerDto customerDto) {
        Mono<PersonalCustomerDto> customerDtoReqMono = Mono.just(customerDto);
        Mono<PersonalCustomerDto> customerDtoDbMono = getById(id);
        return customerDtoReqMono.zipWith(customerDtoDbMono, (customerDtoReq, customerDtoDb) ->
                        CustomerMapper.toModel(customerDtoDb.toBuilder()
                                .firstName(customerDtoReq.getFirstName())
                                .lastName(customerDtoReq.getLastName())
                                .dni(customerDtoReq.getDni())
                                .build()))
                .flatMap(customer -> repo.save(customer).map(CustomerMapper::toDto));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return getById(id).flatMap(customer -> repo.deleteById(id));
    }

}
