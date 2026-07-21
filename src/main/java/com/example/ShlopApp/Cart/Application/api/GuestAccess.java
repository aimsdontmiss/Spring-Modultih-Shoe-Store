package com.example.ShlopApp.Cart.Application.api;


import com.example.ShlopApp.Identity.Application.api.OrderAccess;

import java.util.UUID;

public record GuestAccess(String guestId)
        implements CartAccessContext, OrderAccess {
    public static GuestAccess of(String guestId) {
        return new GuestAccess(guestId);
    }

    @Override
    public UUID orderId() {
        return guestId == null ? null : UUID.nameUUIDFromBytes(guestId.getBytes());
    }
}
