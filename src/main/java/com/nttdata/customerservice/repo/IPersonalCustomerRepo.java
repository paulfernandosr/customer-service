package com.nttdata.customerservice.repo;

import com.nttdata.customerservice.model.PersonalCustomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IPersonalCustomerRepo extends ReactiveMongoRepository<PersonalCustomer, String> {

    Mono<Boolean> existsByDni(String dni);

}
