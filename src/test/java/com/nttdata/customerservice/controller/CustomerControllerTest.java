package com.nttdata.customerservice.controller;

import com.nttdata.customerservice.dto.CustomerDto;
import com.nttdata.customerservice.dto.ProductDto;
import com.nttdata.customerservice.model.Customer;
import com.nttdata.customerservice.repo.ICustomerRepo;
import com.nttdata.customerservice.service.IBankAccountService;
import com.nttdata.customerservice.service.ICreditService;
import com.nttdata.customerservice.service.impl.CustomerServiceImpl;
import com.nttdata.customerservice.util.Constants;
import com.nttdata.customerservice.util.CustomerMapper;
import com.nttdata.customerservice.util.RequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private RequestValidator validator;

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

        Mockito.when(customerService.getAll()).thenReturn(Flux.fromIterable(customers));

        client.get()
                .uri(Constants.CUSTOMER_CONTROLLER + Constants.GET_ALL_METHOD)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CustomerDto.class)
                .hasSize(1);
    }

    @Test
    void getAllProductsById() {
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

        Mockito.when(customerService.getAllProductsById("13d54849519dc31e154c27aa")).thenReturn(Flux.fromIterable(products));

        client.get()
                .uri(Constants.CUSTOMER_CONTROLLER + Constants.GET_ALL_PRODUCTS_BY_ID_METHOD, "13d54849519dc31e154c27aa")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ProductDto.class)
                .hasSize(1);
    }

}
