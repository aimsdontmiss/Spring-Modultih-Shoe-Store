package com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects;

import com.example.ShlopApp.Identity.Application.api.OrderAccess;

import java.util.UUID;

public record OwnerId(
        UUID ownerId
) {
    public static OwnerId of(UUID ownerId) {
        return new OwnerId(ownerId);
    }

    public static OwnerId of(OrderAccess access) {
        return new OwnerId(access.orderId());
    }

    public static OwnerId create() {
        return new OwnerId(UUID.randomUUID());
    }

}
