package com.example.ShlopApp.Cart.Domain.model.ValueObjects;

import java.util.UUID;

public sealed interface CartOwner
        permits SessionOwner, CustomerOwner {
    UUID getOwnerId();
}


