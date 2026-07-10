package com.example.ShlopApp.Cart.Domain.model.ValueObjects;

public record CustomerOwner(String value) implements CartOwner {

    @Override
    public String getOwnerId() {
        return value;
    }

    public static CustomerOwner of(String ownerId) {
        return new CustomerOwner(ownerId);
    }
}


