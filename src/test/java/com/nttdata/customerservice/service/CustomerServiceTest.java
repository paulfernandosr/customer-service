package com.nttdata.customerservice.service;

import com.nttdata.customerservice.dto.CustomerDto;
import com.nttdata.customerservice.model.Customer;
import com.nttdata.customerservice.repo.ICustomerRepo;
import com.nttdata.customerservice.service.impl.CustomerServiceImpl;
import com.nttdata.customerservice.util.Constants;
import com.nttdata.customerservice.util.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private ICustomerRepo repo;

    @Test
    void getAll() {
        List<Customer> customers = List.of(
                Customer.builder()
                        .id("62d54849519dc31e154c27cc")
                        .firstName("firstname")
                        .lastName("lastName")
                        .dni("72923152")
                        .type(Constants.PERSONAL_CUSTOMER)
                        .build(),
                Customer.builder()
                        .id("62d54849519dc31e154c27cc")
                        .firstName("firstname")
                        .lastName("lastName")
                        .dni("72923152")
                        .type(Constants.PERSONAL_CUSTOMER)
                        .build());

        Mockito.when(repo.findAll()).thenReturn(Flux.fromIterable(customers));

        StepVerifier.create(customerService.getAll())
                .expectNextCount(2)
                .verifyComplete();
    }

}
