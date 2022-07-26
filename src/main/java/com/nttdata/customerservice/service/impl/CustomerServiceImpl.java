package com.nttdata.customerservice.service.impl;

import com.nttdata.customerservice.dto.*;
import com.nttdata.customerservice.exception.CustomerNotFoundException;
import com.nttdata.customerservice.exception.DuplicateCustomerException;
import com.nttdata.customerservice.model.Customer;
import com.nttdata.customerservice.repo.ICustomerRepo;
import com.nttdata.customerservice.service.IBankAccountService;
import com.nttdata.customerservice.service.ICreditService;
import com.nttdata.customerservice.service.ICustomerService;
import com.nttdata.customerservice.util.Constants;
import com.nttdata.customerservice.util.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepo repo;
    private final ICreditService creditService;
    private final IBankAccountService bankAccountService;

    @Override
    public Flux<CustomerDto> getAll() {
        return repo.findAll().map(CustomerMapper::toCustomerDto);
    }

    @Override
    public Flux<ProductDto> getProductsById(String id) {
        return Mono.zip(creditService.getCreditsByCustomerId(id).collectList(), bankAccountService.getBankAccountsByCustomerId(id).collectList())
                .flatMapIterable(tuple -> Stream.concat(tuple.getT1().stream(), tuple.getT2().stream()).collect(Collectors.toList()));
    }

    @Override
    public Mono<CustomerDto> getById(String id) {
        return repo.findById(id)
                .switchIfEmpty(throwCustomerNotFoundException(id))
                .map(CustomerMapper::toCustomerDto);
    }

    @Override
    public Mono<CustomerDto> registerPersonalCustomer(PersonalCustomerDto customerDto) {
        return Mono.just(CustomerMapper.toCustomer(customerDto))
                .filterWhen(customer -> repo.existsByDni(customer.getDni()).map(exists -> !exists))
                .switchIfEmpty(throwDuplicateCustomerException(customerDto.getDni()))
                .map(customer -> customer.toBuilder().id(null).type(Constants.PERSONAL_CUSTOMER).build())
                .flatMap(repo::save)
                .map(CustomerMapper::toCustomerDto);
    }

    @Override
    public Mono<CustomerDto> registerBusinessCustomer(BusinessCustomerDto customerDto) {
        return Mono.just(CustomerMapper.toCustomer(customerDto))
                .map(customer -> customer.toBuilder().id(null).type(Constants.BUSINESS_CUSTOMER).build())
                .flatMap(repo::save)
                .map(CustomerMapper::toCustomerDto);
    }

    @Override
    public Mono<CustomerDto> updateById(String id, CustomerDto customerDto) {
        return getById(id).map(existingCustomerDto -> updateCustomerFields(existingCustomerDto, customerDto))
                .map(CustomerMapper::toCustomer)
                .flatMap(repo::save)
                .map(CustomerMapper::toCustomerDto);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return getById(id).flatMap(customer -> repo.deleteById(id));
    }

    private CustomerDto updateCustomerFields(CustomerDto existingCustomerDto, CustomerDto modifiedCustomerDto) {
        return existingCustomerDto.toBuilder()
                .firstName(modifiedCustomerDto.getFirstName())
                .lastName(modifiedCustomerDto.getLastName())
                .dni(modifiedCustomerDto.getDni())
                .name(modifiedCustomerDto.getName())
                .city(modifiedCustomerDto.getCity())
                .address(modifiedCustomerDto.getAddress())
                .type(modifiedCustomerDto.getType())
                .build();
    }

    private Mono<Customer> throwCustomerNotFoundException(String id) {
        return Mono.error(new CustomerNotFoundException(String.format(Constants.CUSTOMER_NOT_FOUND, Constants.ID, id)));
    }

    private Mono<Customer> throwDuplicateCustomerException(String dni) {
        return Mono.error(new DuplicateCustomerException(String.format(Constants.DUPLICATE_CUSTOMER, Constants.DNI, dni)));
    }

}
