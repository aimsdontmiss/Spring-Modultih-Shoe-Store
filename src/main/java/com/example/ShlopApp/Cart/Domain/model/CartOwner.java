package com.example.ShlopApp.Cart.Domain.model;

import com.example.ShlopApp.Cart.Domain.model.ValueObjects.OwnerId;

import java.util.UUID;

public interface CartOwner {
    OwnerId getOwnerId();
}
