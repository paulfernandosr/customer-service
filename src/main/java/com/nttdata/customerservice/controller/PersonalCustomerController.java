package com.nttdata.customerservice.controller;

import com.nttdata.customerservice.dto.PersonalCustomerDto;
import com.nttdata.customerservice.service.IPersonalCustomerService;
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
@RequestMapping(Constants.CONTROLLER_PERSONAL_BASE_PATH)
public class PersonalCustomerController {

    private final IPersonalCustomerService service;
    private final RequestValidator validator;

    @GetMapping(Constants.CONTROLLER_METHOD_GET_ALL)
    public Mono<ResponseEntity<Flux<PersonalCustomerDto>>> getAll() {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAll()));
    }

    @GetMapping(Constants.CONTROLLER_METHOD_GET_BY_ID)
    public Mono<ResponseEntity<PersonalCustomerDto>> getById(@PathVariable(Constants.ID) String id) {
        return service.getById(id).map(customer -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer));
    }

    @PostMapping(Constants.CONTROLLER_METHOD_REGISTER)
    public Mono<ResponseEntity<PersonalCustomerDto>> register(@RequestBody PersonalCustomerDto customer, final ServerHttpRequest request) {
        return validator.validate(customer)
                .flatMap(validatedCustomer -> service.register(validatedCustomer)
                        .map(registeredCustomer -> ResponseEntity.created(URI.create(request.getURI() + Constants.SLASH + registeredCustomer.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(registeredCustomer)));
    }

    @PutMapping(Constants.CONTROLLER_METHOD_UPDATE_BY_ID)
    public Mono<ResponseEntity<PersonalCustomerDto>> updateById(@PathVariable(Constants.ID) String id, @RequestBody PersonalCustomerDto customer) {
        return validator.validate(customer)
                .flatMap(validatedCustomer -> service.updateById(id, validatedCustomer)
                        .map(updatedCustomer -> ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(updatedCustomer)));
    }

    @DeleteMapping(Constants.CONTROLLER_METHOD_DELETE_BY_ID)
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable(Constants.ID) String id) {
        return service.deleteById(id).thenReturn(new ResponseEntity<>(HttpStatus.OK));
    }

}
