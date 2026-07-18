package com.example.ShlopApp.Cart.Application.internals.command;

import com.example.ShlopApp.Cart.Domain.model.CartItem;

import java.math.BigDecimal;
import java.util.UUID;

public record RemoveItemCommand(
        UUID cartId,
        UUID variantId,
        BigDecimal unitPrice,
        int quantity
) { }
