package com.nttdata.customerservice.config;

import com.nttdata.customerservice.util.Constants;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Builder
@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesConfig {

    @Value(Constants.BANK_ACCOUNT_SERVICE_BASE_URL)
    private String bankAccountServiceBaseUrl;

    @Value(Constants.METHOD_GET_BANK_ACCOUNTS_BY_CUSTOMER_ID)
    private String methodGetBankAccountsByCustomerId;

    @Value(Constants.CREDIT_SERVICE_BASE_URL)
    private String creditServiceBaseUrl;

    @Value(Constants.METHOD_GET_CREDITS_BY_CUSTOMER_ID)
    private String methodGetCreditsByCustomerIdMethod;

}
