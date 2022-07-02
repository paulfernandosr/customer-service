package com.nttdata.customerservice.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends DomainException {

    public CustomerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
