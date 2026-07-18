package com.example.ShlopApp.Commerce.Domain.Order.exception;

public class UnauthorizedCheckoutException extends RuntimeException {
    public UnauthorizedCheckoutException(String message) {
        super(message);
    }
}
