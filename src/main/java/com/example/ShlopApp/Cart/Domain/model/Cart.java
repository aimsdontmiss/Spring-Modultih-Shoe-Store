package com.example.ShlopApp.Cart.Domain.model;

import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Cart {
    private CartId cartId;
    private CartOwner ownerId;
    private List<CartItem> items;
    public final Instant createdAt;
    public  Instant updatedAt;

    public Cart(
            CartId cartId,
            CartOwner ownerId,
            List<CartItem> items,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.cartId = cartId;
        this.ownerId = ownerId;
        this.items = new ArrayList<>(items);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.touch();
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(CartItem::getUnitTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addToCart(
            UUID variantId,
            BigDecimal unitPrice,
            int quantity
    ){
        CartItem existing = items.stream()
                .filter(item -> item.getVariantId().equals(variantId))
                .findFirst()
                .orElse(null);

        if(existing != null){
            existing.increaseQuantity(quantity);
        }
        else {
            items.add(
                    CartItem.create(
                            variantId,
                            unitPrice,
                            quantity
                    )
            );
        }
    }

    public void removeFromCart(
        UUID variantId,
        BigDecimal unitPrice,
        int quantity
    ) throws RuntimeException {

        CartItem existing = items.stream()
                .filter(item -> item.getVariantId().equals(variantId))
                .findFirst()
                .orElse(null);


        if (existing == null) {
            throw new RuntimeException("Item not found");
        }

        while (quantity > 0 && unitPrice.equals(existing.getUnitPrice())) {
            try {
                if (existing.getQuantity() == quantity) {
                    items.remove(existing);
                    break;
                } else if (existing.getQuantity() > quantity) {
                    existing.decreaseQuantity(quantity);
                    break;
                }
            } catch (Exception e) {
                throw new RuntimeException("\nError: " + e.getMessage());
            }
        }

        if (items.isEmpty()) {
            items.remove(existing);
        }
    }

    public void clearCart() {
        items.clear();
    }

    public void touch() {
        this.updatedAt = Instant.now();
    }

    public void changeOwner(CartOwner ownerId) {
        this.ownerId = ownerId;
    }

    public static Cart create(CartOwner owner) {

        return new Cart(
                new CartId(UUID.randomUUID()),
                owner,
                new ArrayList<>(),
                Instant.now(),
                Instant.now()
        );
    }

    public boolean belongsTo(CartOwner owner) {
        return this.ownerId.equals(owner);
    }

}




