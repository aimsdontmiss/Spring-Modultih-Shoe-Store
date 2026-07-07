package com.example.ShlopApp.Cart.Domain.model.ValueObjects;

import java.util.UUID;

public record OwnerId(
        String ownerId
) {
    public static OwnerId of(String ownerId) {
        return new OwnerId(ownerId);
    }

}
