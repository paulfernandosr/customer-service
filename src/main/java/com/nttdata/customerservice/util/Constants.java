package com.nttdata.customerservice.util;

public class Constants {

    private Constants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static final String FIVE_CURLY_BRACKETS = "{}{}{}{}{}";
    public static final String SLASH = "/";
    public static final String COLON = ": ";
    public static final String IN = " in ";

    // Keys
    public static final String TIMESTAMP = "timestamp";
    public static final String REQUEST_ID = "requestId";
    public static final String MESSAGE = "message";
    public static final String STATUS = "status";
    public static final String ERROR = "error";
    public static final String VALIDATIONS = "validations";
    public static final String ID = "id";
    public static final String DNI = "dni";

    // Exception messages
    public static final String CUSTOMER_NOT_FOUND = "Customer not found with %s: %s";
    public static final String NULL_REQUEST = "The request is null";
    public static final String VIOLATED_CONSTRAINTS = "Restrictions were violated";
    public static final String NOT_NULL = "Must not be null";
    public static final String DUPLICATE_CUSTOMER = "There is already a customer with %s: %s";
    public static final String UTILITY_CLASS = "Utility class";

    // Collections
    public static final String CUSTOMERS_COLLECTION = "customers";

    // Controller paths
    public static final String CUSTOMER_CONTROLLER = "/customers";

    // Method paths
    public static final String GET_ALL_METHOD = "/all";
    public static final String GET_BY_ID_METHOD = "/{" + ID + "}";
    public static final String GET_ALL_PRODUCTS_BY_ID_METHOD = "/{" + ID + "}/products";
    public static final String REGISTER_PERSONAL_CUSTOMER_METHOD = "/personal-customers";
    public static final String REGISTER_BUSINESS_CUSTOMER_METHOD = "/business-customers";
    public static final String UPDATE_BY_ID_METHOD = "/{" + ID + "}";
    public static final String DELETE_BY_ID_METHOD = "/{" + ID + "}";

    // Types
    public static final String PERSONAL_CUSTOMER = "CUSTOMER.PERSONAL";
    public static final String BUSINESS_CUSTOMER = "CUSTOMER.BUSINESS";

    // Bank account service
    public static final String BANK_ACCOUNT_SERVICE_BASE_URL = "${bankAccountService.baseUrl:http://localhost:8099/bank-account-service}";
    public static final String METHOD_GET_BANK_ACCOUNTS_BY_CUSTOMER_ID = "${bankAccountService.method.getBankAccountsByCustomerId:/bank-accounts/customers/{customerId}}";


    // Credit service
    public static final String CREDIT_SERVICE_BASE_URL = "${creditService.baseUrl:http://localhost:8099/bank-account-service}";
    public static final String METHOD_GET_CREDITS_BY_CUSTOMER_ID = "${creditService.method.getCreditsByCustomerId:/bank-accounts/customers/{customerId}";

}
