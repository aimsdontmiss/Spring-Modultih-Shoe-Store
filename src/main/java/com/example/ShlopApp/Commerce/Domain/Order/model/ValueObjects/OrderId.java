package com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects;

import java.util.UUID;

public record OrderId(
        UUID orderId
) {
    public static OrderId of(UUID orderId) {
        return new OrderId(orderId);
    }

    public static OrderId create() {
        return new OrderId(UUID.randomUUID());
    }
}
