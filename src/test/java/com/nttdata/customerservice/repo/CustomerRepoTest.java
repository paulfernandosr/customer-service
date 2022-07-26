package com.nttdata.customerservice.repo;

import com.nttdata.customerservice.model.Customer;
import com.nttdata.customerservice.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class CustomerRepoTest {

    @Autowired
    private ICustomerRepo repo;

    @Test
    void findAll() {
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

        Flux<Customer> publisher = repo.findAll().thenMany(Flux.fromIterable(customers));

        StepVerifier.create(publisher)
                .expectNextCount(2)
                .verifyComplete();
    }


}
