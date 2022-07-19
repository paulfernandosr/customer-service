package com.nttdata.customerservice.config;

import com.nttdata.customerservice.util.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

    @Value(Constants.BANK_ACCOUNT_SERVICE_BASE_URL)
    public String bankAccountServiceBaseUrl;

    @Value(Constants.GET_BANK_ACCOUNTS_BY_CUSTOMER_ID_METHOD)
    public String getBankAccountsByCustomerIdMethod;

    @Value(Constants.CREDIT_SERVICE_BASE_URL)
    public String creditServiceBaseUrl;

    @Value(Constants.GET_CREDITS_BY_CUSTOMER_ID_METHOD)
    public String getCreditsByCustomerIdMethod;

}
