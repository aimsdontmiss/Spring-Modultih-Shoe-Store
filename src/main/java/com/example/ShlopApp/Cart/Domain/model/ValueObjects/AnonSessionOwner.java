package com.example.ShlopApp.Cart.Domain.model.ValueObjects;

import com.example.ShlopApp.Cart.Domain.model.CartOwner;

public record AnonSessionOwner(String value)
        implements CartOwner {
    @Override
    public OwnerId getOwnerId() {
        return OwnerId.of(value);
    }
}

