package com.ecommerce.productservice.exception;

public class InvalidProductIdException extends RuntimeException{

    public InvalidProductIdException(String message) {
        super(message);
    }
}
