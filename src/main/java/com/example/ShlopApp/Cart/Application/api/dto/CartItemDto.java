package com.example.ShlopApp.Cart.Application.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemDto(
    UUID cartItemId,
    UUID variantId,
    BigDecimal unitPrice,
    int quantity
) { }
