package com.nttdata.customerservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.customerservice.config.PropertiesConfig;
import com.nttdata.customerservice.dto.ProductDto;
import com.nttdata.customerservice.service.impl.BankAccountServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class)
class BankAccountServiceTest {

    private BankAccountServiceImpl bankAccountService;
    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize() {
        mockWebServer = new MockWebServer();
        PropertiesConfig propertiesConfig = PropertiesConfig.builder()
                .bankAccountServiceBaseUrl(mockWebServer.url("/bank-account-service").toString())
                .getBankAccountsByCustomerIdMethod("/bank-accounts/customers/{customerId}")
                .build();
        bankAccountService = new BankAccountServiceImpl(WebClient.builder(), propertiesConfig);
    }

    @Test
    void getAllProductsById() throws JsonProcessingException {
        List<ProductDto> bankAccounts = List.of(
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

        mockWebServer.enqueue(
                new MockResponse().setResponseCode(200)
                        .setBody(new ObjectMapper().writeValueAsString(bankAccounts))
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );

        StepVerifier.create(bankAccountService.getAllByCustomerId("13d54849519dc31e154c27aa"))
                .expectNextCount(1)
                .verifyComplete();
    }

}
