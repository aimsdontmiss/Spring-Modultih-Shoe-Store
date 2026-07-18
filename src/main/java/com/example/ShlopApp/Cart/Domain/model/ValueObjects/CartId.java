package com.example.ShlopApp.Cart.Domain.model.ValueObjects;

import java.util.UUID;

public record CartId(
        UUID cartId
) {
    public static CartId of(UUID cartId) {
        return new CartId(cartId);
    }

    public static CartId create() {
        return new CartId(UUID.randomUUID());
    }
}
