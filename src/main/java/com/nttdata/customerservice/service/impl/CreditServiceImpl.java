package com.nttdata.customerservice.service.impl;

import com.nttdata.customerservice.config.PropertiesConfig;
import com.nttdata.customerservice.dto.ProductDto;
import com.nttdata.customerservice.exception.DomainException;
import com.nttdata.customerservice.service.ICreditService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeoutException;

@Service
public class CreditServiceImpl implements ICreditService {

    private final WebClient webClient;
    private final PropertiesConfig propertiesConfig;

    public CreditServiceImpl(WebClient.Builder webClientBuilder, PropertiesConfig propertiesConfig) {
        this.webClient = webClientBuilder.baseUrl(propertiesConfig.getCreditServiceBaseUrl()).build();
        this.propertiesConfig = propertiesConfig;
    }

    @Override
    @CircuitBreaker(name = "creditService", fallbackMethod = "fallbackGetAllByCustomerId")
    @TimeLimiter(name = "creditService", fallbackMethod = "fallbackGetAllByCustomerId")
    public Flux<ProductDto> getCreditsByCustomerId(String customerId) {
        return webClient.get().uri(propertiesConfig.getMethodGetCreditsByCustomerIdMethod(), customerId)
                .retrieve()
                .bodyToFlux(ProductDto.class);
    }

    private Flux<ProductDto> fallbackGetAllByCustomerId(String customerId, WebClientResponseException e) {
        return Flux.error(new DomainException(e.getStatusCode(), e.getMessage()));
    }

    private Flux<ProductDto> fallbackGetAllByCustomerId(String customerId, TimeoutException e) {
        return Flux.error(new DomainException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

}
