package com.example.ShlopApp.Cart.Application.internals.command;

import com.example.ShlopApp.Cart.Domain.model.CartItem;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Catalog.Domain.model.Variant;

import java.math.BigDecimal;
import java.util.UUID;

public record AddToCartCommand(
        UUID cartId,
        UUID owner,
        UUID variantId,
        BigDecimal unitPrice,
        int quantity
) { }
