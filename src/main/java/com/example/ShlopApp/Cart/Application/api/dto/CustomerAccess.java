package com.example.ShlopApp.Cart.Application.api.dto;

import com.example.ShlopApp.Identity.Application.api.OrderAccess;

import java.util.UUID;

public record CustomerAccess(UUID customerId)
        implements CartAccessContext, OrderAccess {

    public static CustomerAccess of(UUID customerId) {
        return new CustomerAccess(customerId);
    }

    @Override
    public UUID orderId() {
        return customerId;
    }
}