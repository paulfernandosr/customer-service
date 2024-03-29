package com.nttdata.customerservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.customerservice.config.PropertiesConfig;
import com.nttdata.customerservice.dto.ProductDto;
import com.nttdata.customerservice.service.impl.BankAccountServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class BankAccountServiceTest {

    private BankAccountServiceImpl bankAccountService;
    private static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        PropertiesConfig propertiesConfig = PropertiesConfig.builder()
                .bankAccountServiceBaseUrl(mockBackEnd.url("/bank-account-service").toString())
                .methodGetBankAccountsByCustomerId("/bank-accounts/customers/{customerId}")
                .build();
        bankAccountService = new BankAccountServiceImpl(WebClient.builder(), propertiesConfig);
    }

    @Test
    void getAllProductsById() throws JsonProcessingException, InterruptedException {
        List<ProductDto> bankAccounts = List.of(
                ProductDto.builder()
                        .id("62d9880f2e235e0d78bab55b")
                        .accountNumber("12345")
                        .cci("12345")
                        .balance(30.0)
                        .customerId("62d9777c025a770fc6e3e7c5")
                        .monthlyMovementLimit(3)
                        .transactionLimit(3)
                        .type("ACCOUNT.SAVINGS.PERSONAL")
                        .build());

        mockBackEnd.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(bankAccounts))
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        StepVerifier.create(bankAccountService.getBankAccountsByCustomerId("62d9777c025a770fc6e3e7c5"))
                .expectNext(bankAccounts.get(0))
                .verifyComplete();

        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/bank-account-service/bank-accounts/customers/62d9777c025a770fc6e3e7c5", recordedRequest.getPath());
    }

}
