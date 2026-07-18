package com.example.ShlopApp.Cart.Application.api;

import java.util.UUID;

public record CustomerAccess(UUID customerId)
        implements CartAccessContext {
}