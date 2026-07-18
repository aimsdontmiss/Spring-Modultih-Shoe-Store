package com.example.ShlopApp.Cart.Domain.model.ValueObjects;

import java.util.UUID;

public record CustomerOwner(UUID ownerId) implements CartOwner {

    @Override
    public UUID getOwnerId() {
        return ownerId;
    }

    public static CustomerOwner of(UUID ownerId) {
        return new CustomerOwner(ownerId);
    }

}


