package com.example.ShlopApp.Cart.Application.api.dto;

import com.example.ShlopApp.Cart.Domain.model.CartItem;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record CartDto(
    UUID cartId,
    String ownerId,
    List<CartItem> items,
    BigDecimal totalPrice,
    Instant createdAt,
    Instant updatedAt
) { }


