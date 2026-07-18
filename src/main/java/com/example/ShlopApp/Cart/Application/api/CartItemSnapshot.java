package com.example.ShlopApp.Cart.Application.api;

import com.example.ShlopApp.Cart.Domain.model.CartItem;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemSnapshot(
        UUID variantId,
        int quantity,
        BigDecimal unitPrice
) {
    public static CartItemSnapshot of(CartItem item) {
        return new CartItemSnapshot(
                item.getVariantId(),
                item.getQuantity(),
                item.getUnitPrice()
        );
    }
}
