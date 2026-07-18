package com.example.ShlopApp.Cart.Domain.exception;


import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(CartId cartId) {
        super(String.format("CartId of %u not found", cartId.cartId()));
    }
}

