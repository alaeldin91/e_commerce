package com.alaeldin.productservice.exception.existdata;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductAlreadyExistsException extends RuntimeException {
    private String message;

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
