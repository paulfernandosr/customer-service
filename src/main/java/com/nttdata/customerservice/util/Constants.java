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

    // Paths
    public static final String CONTROLLER_PERSONAL_BASE_PATH = "${controller.personal.base-path}";
    public static final String CONTROLLER_BUSINESS_BASE_PATH = "${controller.business.base-path}";
    public static final String CONTROLLER_METHOD_GET_ALL = "${controller.method.get-all}";
    public static final String CONTROLLER_METHOD_GET_BY_ID = "${controller.method.get-by-id}";
    public static final String CONTROLLER_METHOD_REGISTER = "${controller.method.register}";
    public static final String CONTROLLER_METHOD_UPDATE_BY_ID = "${controller.method.update-by-id}";
    public static final String CONTROLLER_METHOD_DELETE_BY_ID = "${controller.method.delete-by-id}";

}
