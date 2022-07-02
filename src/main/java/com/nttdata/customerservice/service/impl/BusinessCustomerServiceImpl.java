package com.nttdata.customerservice.service.impl;

import com.nttdata.customerservice.dto.BusinessCustomerDto;
import com.nttdata.customerservice.exception.CustomerNotFoundException;
import com.nttdata.customerservice.model.BusinessCustomer;
import com.nttdata.customerservice.repo.IBusinessCustomerRepo;
import com.nttdata.customerservice.service.IBusinessCustomerService;
import com.nttdata.customerservice.util.Constants;
import com.nttdata.customerservice.util.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BusinessCustomerServiceImpl implements IBusinessCustomerService {

    private final IBusinessCustomerRepo repo;

    @Override
    public Flux<BusinessCustomerDto> getAll() {
        return repo.findAll().map(CustomerMapper::toDto);
    }

    @Override
    public Mono<BusinessCustomerDto> getById(String id) {
        return repo.findById(id)
                .map(CustomerMapper::toDto)
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(String.format(Constants.CUSTOMER_NOT_FOUND, Constants.ID, id))));
    }

    @Override
    public Mono<BusinessCustomerDto> register(BusinessCustomerDto customerDto) {
        BusinessCustomer customer = CustomerMapper.toModel(customerDto).toBuilder().id(null).build();
        return repo.save(customer).map(CustomerMapper::toDto);
    }

    @Override
    public Mono<BusinessCustomerDto> updateById(String id, BusinessCustomerDto customerDto) {
        Mono<BusinessCustomerDto> customerDtoReqMono = Mono.just(customerDto);
        Mono<BusinessCustomerDto> customerDtoDbMono = getById(id);
        return customerDtoReqMono.zipWith(customerDtoDbMono, (customerDtoReq, customerDtoDb) ->
                        CustomerMapper.toModel(customerDtoDb.toBuilder()
                                .name(customerDtoReq.getName())
                                .city(customerDtoReq.getCity())
                                .address(customerDtoReq.getAddress())
                                .build()))
                .flatMap(customer -> repo.save(customer).map(CustomerMapper::toDto));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return getById(id).flatMap(customer -> repo.deleteById(id));
    }

}
