package com.nttdata.customerservice.controller;

import com.nttdata.customerservice.dto.BusinessCustomerDto;
import com.nttdata.customerservice.dto.CustomerDto;
import com.nttdata.customerservice.dto.PersonalCustomerDto;
import com.nttdata.customerservice.service.ICustomerService;
import com.nttdata.customerservice.util.Constants;
import com.nttdata.customerservice.util.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.CUSTOMER_CONTROLLER)
public class CustomerController {

    private final ICustomerService service;
    private final RequestValidator validator;

    @GetMapping(Constants.GET_ALL_METHOD)
    public Mono<ResponseEntity<Flux<CustomerDto>>> getAll() {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAll()));
    }

    @GetMapping(Constants.GET_BY_ID_METHOD)
    public Mono<ResponseEntity<CustomerDto>> getById(@PathVariable(Constants.ID) String id) {
        return service.getById(id).map(customer -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer));
    }

    @PostMapping(Constants.REGISTER_PERSONAL_CUSTOMER_METHOD)
    public Mono<ResponseEntity<CustomerDto>> registerPersonalCustomer(@RequestBody PersonalCustomerDto customer, final ServerHttpRequest request) {
        return validator.validate(customer)
                .flatMap(validatedCustomer -> service.registerPersonalCustomer(customer))
                .map(registeredCustomer -> ResponseEntity.created(URI.create(request.getURI() + Constants.SLASH + registeredCustomer.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(registeredCustomer));
    }

    @PostMapping(Constants.REGISTER_BUSINESS_CUSTOMER_METHOD)
    public Mono<ResponseEntity<CustomerDto>> registerBusinessCustomer(@RequestBody BusinessCustomerDto customer, final ServerHttpRequest request) {
        return validator.validate(customer)
                .flatMap(validatedCustomer -> service.registerBusinessCustomer(customer))
                .map(registeredCustomer -> ResponseEntity.created(URI.create(request.getURI() + Constants.SLASH + registeredCustomer.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(registeredCustomer));
    }

    @PutMapping(Constants.UPDATE_BY_ID_METHOD)
    public Mono<ResponseEntity<CustomerDto>> updateById(@PathVariable(Constants.ID) String id, @RequestBody CustomerDto customer) {
        return validator.validate(customer)
                .flatMap(validatedCustomer -> service.updateById(id, customer))
                .map(updatedCustomer -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updatedCustomer));
    }

    @DeleteMapping(Constants.DELETE_BY_ID_METHOD)
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable(Constants.ID) String id) {
        return service.deleteById(id).thenReturn(new ResponseEntity<>(HttpStatus.OK));
    }

}
