package com.example.ShlopApp.Cart.Domain.model.ValueObjects;

import java.util.UUID;

public record SessionOwner(UUID ownerId)
        implements CartOwner {

    @Override
    public UUID getOwnerId() {
        return ownerId;
    }

    public static SessionOwner of(UUID ownerId) {
        return new SessionOwner(ownerId);
    }
}

