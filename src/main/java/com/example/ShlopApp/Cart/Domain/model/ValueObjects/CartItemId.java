package com.example.ShlopApp.Cart.Domain.model.ValueObjects;

import java.util.UUID;

public record CartItemId(
        UUID cartItemId
) {
    public static CartItemId of(UUID cartItemId) {
        return new CartItemId(cartItemId);
    }

}
