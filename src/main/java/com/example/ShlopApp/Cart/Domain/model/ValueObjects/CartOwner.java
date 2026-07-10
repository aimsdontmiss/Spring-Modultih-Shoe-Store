package com.example.ShlopApp.Cart.Domain.model.ValueObjects;

public sealed interface CartOwner
        permits SessionOwner, CustomerOwner {
    String getOwnerId();
}


