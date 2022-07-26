package com.nttdata.customerservice.service;

import com.nttdata.customerservice.dto.ProductDto;
import reactor.core.publisher.Flux;

public interface IBankAccountService {

    Flux<ProductDto> getBankAccountsByCustomerId(String customerId);

}
