package com.example.ShlopApp.Cart.Domain.exception;

import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;

public class UnauthorizedCartAccessException extends RuntimeException {

    public UnauthorizedCartAccessException() {
        super("Unauthorized access to cart: ");
    }
}
