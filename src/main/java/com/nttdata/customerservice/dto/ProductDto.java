package com.nttdata.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private final String id;

    private final String cardNumber;
    private final String cvv;
    private final Double creditLine;
    private final LocalDate expirationDate;
    private final Double amountToPay;
    private final Double amountPaid;
    private final LocalDate paymentDate;

    private final String accountNumber;
    private final String cci;
    private final String maintenanceFee;
    private final Integer monthlyMovementLimit;
    private final Double monthlyMinimumBalance;
    private final Integer transactionLimit;

    private final Double balance;
    private final String type;
    private final String customerId;

}
