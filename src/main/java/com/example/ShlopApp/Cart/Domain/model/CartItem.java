package com.example.ShlopApp.Cart.Domain.model;

import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartItemId;
import com.example.ShlopApp.Catalog.Domain.model.Variant;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class CartItem {
    private CartItemId cartItemId;
    private UUID variantId;
    private BigDecimal unitPrice;
    private int quantity;

    public CartItem(
            CartItemId cartItemId,
            UUID variantId,
            BigDecimal unitPrice,
            int quantity
    ) {
        this.cartItemId = cartItemId;
        this.variantId = variantId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public BigDecimal getUnitTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void increaseQuantity(int delta) {
        if (delta <= 0) throw new IllegalArgumentException();
        this.quantity += delta;
    }

    public void decreaseQuantity(int delta) {
        if (delta <= 0) throw new IllegalArgumentException();
        this.quantity -= delta;
    }

    public CartItem merge(CartItem other) {
        return new CartItem(
                this.cartItemId,
                this.variantId,
                this.unitPrice,
                this.quantity + other.quantity
        );
    }

    public void increment() {
        this.quantity++;
    }

    public void decrement() {
        this.quantity--;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException();
        this.quantity = quantity;
    }

//    public static CartItem create(UUID variantId, BigDecimal unitPrice, int quantity) {
//        return new CartItem(
//                CartItemId.create(),
//                variantId,
//                unitPrice,
//                quantity
//        );
//    }

    public static CartItem create(UUID variantId, BigDecimal unitPrice, int quantity) {
        return new CartItem(
                null,
                variantId,
                unitPrice,
                quantity
        );
    }
}


