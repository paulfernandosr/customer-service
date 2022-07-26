package com.nttdata.customerservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.customerservice.config.PropertiesConfig;
import com.nttdata.customerservice.dto.ProductDto;
import com.nttdata.customerservice.service.impl.CreditServiceImpl;
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
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class CreditServiceTest {

    private CreditServiceImpl creditService;
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
                .creditServiceBaseUrl(mockBackEnd.url("/credit-service").toString())
                .methodGetCreditsByCustomerIdMethod("/credits/customers/{customerId}")
                .build();
        creditService = new CreditServiceImpl(WebClient.builder(), propertiesConfig);
    }

    @Test
    void getCreditsByCustomerId() throws JsonProcessingException, InterruptedException {
        List<ProductDto> credits = List.of(
                ProductDto.builder()
                        .id("62d9880b4ed80d50de1636e2")
                        .amountToPay(500.0)
                        .amountPaid(0.0)
                        .paymentDate(LocalDate.of(2023, 5, 5))
                        .type("CREDIT.PERSONAL")
                        .customerId("62d9777c025a770fc6e3e7c5")
                        .build());

        mockBackEnd.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(credits))
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        StepVerifier.create(creditService.getCreditsByCustomerId("62d9777c025a770fc6e3e7c5"))
                .expectNext(credits.get(0))
                .verifyComplete();

        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/credit-service/credits/customers/62d9777c025a770fc6e3e7c5", recordedRequest.getPath());
    }
}