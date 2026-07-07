package com.example.ShlopApp.Cart.Domain.model;

import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.OwnerId;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Cart {
    private CartId cartId;
    private OwnerId ownerId;
    private List<CartItem> items;
    public final Instant createdAt;
    public  Instant updatedAt;

    public Cart(
            CartId cartId,
            OwnerId ownerId,
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

//    public void addToCart(CartItem item) {
//        // We initially ensure that the item is not already in the cart //
//        for (CartItem cartItem : items) {
//            // If the item is already in the cart, we increase its quantity by the new quantity //
//            if (cartItem.getVariantId().equals(item.getVariantId())) {
//                cartItem.increaseQuantity(item.getQuantity());
//                this.touch();
//                return;
//            }
//        }
//
//        // If the item is not in the cart, we add it to the cart //
//        items.add(item);
//        this.touch();
//    }

//    public void addToCart(CartItem item) {
//
//        items.stream()
//                .filter(i -> i.getVariantId().equals(item.getVariantId()))
//                .findFirst()
//                .ifPresentOrElse(
//                        existing -> existing.increaseQuantity(item.getQuantity()),
//                        () -> items.add(item)
//                );
//
//        touch();
//    }

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

    public void removeFromCart(CartItem item) {
        // We initially ensure that the item is not already in the cart //
        for (CartItem cartItem : items) {
            // If the item is already in the cart, we increase its quantity by the new quantity //
            if (cartItem.getVariantId().equals(item.getVariantId())) {
                cartItem.decreaseQuantity(item.getQuantity());
            }
        }

        // If the item is not in the cart, we add it to the cart //
        items.add(item);
        this.touch();
    }

    public void touch() {
        this.updatedAt = Instant.now();
    }

    public void changeOwner(OwnerId ownerId) {
        this.ownerId = ownerId;
    }

    public static Cart create(OwnerId owner) {

        return new Cart(
                new CartId(UUID.randomUUID()),
                owner,
                new ArrayList<>(),
                Instant.now(),
                Instant.now()
        );
    }

}




