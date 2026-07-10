package com.example.ShlopApp.Cart.Domain.model.ValueObjects;

public record SessionOwner(String value)
        implements CartOwner {

    @Override
    public String getOwnerId() {
        return value;
    }

    public static SessionOwner of(String ownerId) {
        return new SessionOwner(ownerId);
    }
}

