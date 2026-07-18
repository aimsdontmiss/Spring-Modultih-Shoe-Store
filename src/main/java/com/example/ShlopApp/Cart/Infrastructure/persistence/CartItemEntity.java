package com.example.ShlopApp.Cart.Infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(
    name = "STG_CART_ITEMS",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = { "cart_id", "variant_id" }
        )
    }
)
@Data
@NoArgsConstructor
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    private UUID variantId;
    private BigDecimal unitPrice;
    private int quantity;

    public CartItemEntity(
            UUID variantId,
            BigDecimal unitPrice,
            int quantity
    ) {
        this.variantId = variantId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }
}


