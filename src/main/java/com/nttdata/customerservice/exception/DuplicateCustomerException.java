package com.nttdata.customerservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DuplicateCustomerException extends DomainException {

    public DuplicateCustomerException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
