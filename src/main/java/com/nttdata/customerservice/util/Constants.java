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
    public static final String PERSONAL_CUSTOMERS_COLLECTION = "personalCustomers";
    public static final String BUSINESS_CUSTOMERS_COLLECTION = "businessCustomers";

    // Controller paths
    public static final String PERSONAL_CUSTOMER_CONTROLLER = "${controller.personal-customer.base-path}";
    public static final String BUSINESS_CUSTOMER_CONTROLLER = "${controller.business-customer.base-path}";

    // Method paths
    public static final String GET_ALL_METHOD = "${controller.method.get-all}";
    public static final String GET_BY_ID_METHOD = "${controller.method.get-by-id}";
    public static final String REGISTER_METHOD = "${controller.method.register}";
    public static final String UPDATE_BY_ID_METHOD = "${controller.method.update-by-id}";
    public static final String DELETE_BY_ID_METHOD = "${controller.method.delete-by-id}";

    // Path variables
    public static final String ID_PATH_VARIABLE = "${controller.path-variable.id}";

}
