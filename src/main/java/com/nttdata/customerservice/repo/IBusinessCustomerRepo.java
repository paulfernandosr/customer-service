package com.nttdata.customerservice.repo;

import com.nttdata.customerservice.model.BusinessCustomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IBusinessCustomerRepo extends ReactiveMongoRepository<BusinessCustomer, String> {
}
