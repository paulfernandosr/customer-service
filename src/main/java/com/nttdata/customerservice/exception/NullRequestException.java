package com.nttdata.customerservice.exception;

import org.springframework.http.HttpStatus;

public class NullRequestException extends DomainException {

    public NullRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
