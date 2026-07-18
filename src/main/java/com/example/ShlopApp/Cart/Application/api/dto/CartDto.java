package com.example.ShlopApp.Cart.Application.api.dto;

import com.example.ShlopApp.Cart.Domain.model.CartItem;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record CartDto(
    UUID cartId,
    UUID ownerId,
    List<CartItemDto> items,
    BigDecimal totalPrice,
    Instant createdAt,
    Instant updatedAt
) { }


