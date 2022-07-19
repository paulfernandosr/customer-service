package com.nttdata.customerservice.repo;

import com.nttdata.customerservice.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ICustomerRepo extends ReactiveMongoRepository<Customer, String> {

    Mono<Boolean> existsByDni(String dni);

    Mono<Customer> findByDni(String dni);

}
