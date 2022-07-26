package com.nttdata.customerservice.controller;

import com.nttdata.customerservice.dto.CustomerDto;
import com.nttdata.customerservice.dto.PersonalCustomerDto;
import com.nttdata.customerservice.dto.ProductDto;
import com.nttdata.customerservice.service.impl.CustomerServiceImpl;
import com.nttdata.customerservice.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private CustomerServiceImpl customerService;

    @Test
    void getAll() {
        List<CustomerDto> customers = List.of(
                CustomerDto.builder()
                        .id("62d54849519dc31e154c27cc")
                        .firstName("firstname")
                        .lastName("lastName")
                        .dni("72923152")
                        .type(Constants.PERSONAL_CUSTOMER)
                        .build());

        when(customerService.getAll()).thenReturn(Flux.fromIterable(customers));

        client.get()
                .uri(Constants.CUSTOMER_CONTROLLER + Constants.GET_ALL_METHOD)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CustomerDto.class)
                .hasSize(1)
                .isEqualTo(customers);
    }

    @Test
    void getProductsById() {
        List<ProductDto> products = List.of(
                ProductDto.builder()
                        .id("62d54849519dc31e154c27cc")
                        .accountNumber("12345678")
                        .cci("12345678")
                        .balance(500.0)
                        .customerId("13d54849519dc31e154c27aa")
                        .monthlyMovementLimit(5)
                        .monthlyMinimumBalance(200.0)
                        .type("ACCOUNT.SAVINGS.PERSONAL")
                        .build());

        when(customerService.getProductsById("13d54849519dc31e154c27aa")).thenReturn(Flux.fromIterable(products));

        client.get()
                .uri(Constants.CUSTOMER_CONTROLLER + Constants.GET_PRODUCTS_BY_ID_METHOD, "13d54849519dc31e154c27aa")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ProductDto.class)
                .hasSize(1)
                .isEqualTo(products);
    }

    @Test
    void getById() {
        CustomerDto customer = CustomerDto.builder()
                .id("62d54849519dc31e154c27cc")
                .firstName("firstname")
                .lastName("lastName")
                .dni("72923152")
                .type(Constants.PERSONAL_CUSTOMER)
                .build();

        when(customerService.getById(anyString())).thenReturn(Mono.just(customer));

        client.get()
                .uri(Constants.CUSTOMER_CONTROLLER + Constants.GET_BY_ID_METHOD, "62d54849519dc31e154c27cc")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CustomerDto.class)
                .isEqualTo(customer);
    }

    @Test
    void registerPersonalCustomer() {
        PersonalCustomerDto customerRequest = PersonalCustomerDto.builder()
                .firstName("firstname")
                .lastName("lastName")
                .dni("72923152")
                .build();

        CustomerDto customerResponse = CustomerDto.builder()
                .id("62d54849519dc31e154c27cc")
                .firstName("firstname")
                .lastName("lastName")
                .dni("72923152")
                .type(Constants.PERSONAL_CUSTOMER)
                .build();

        when(customerService.registerPersonalCustomer(any(PersonalCustomerDto.class))).thenReturn(Mono.just(customerResponse));

        client.post()
                .uri(Constants.CUSTOMER_CONTROLLER + Constants.REGISTER_PERSONAL_CUSTOMER_METHOD)
                .bodyValue(customerRequest)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CustomerDto.class)
                .isEqualTo(customerResponse);
    }

}
