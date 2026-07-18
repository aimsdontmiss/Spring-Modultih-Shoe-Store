package com.example.ShlopApp.Cart.Infrastructure.persistence;

import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.CartItem;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.SessionOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartItemId;
import org.springframework.stereotype.Component;

@Component
public class CartPersistenceMapper {

    //  For the Cart Entity...
    public void updateCartEntity(Cart cart, CartEntity entity) {

        entity.setOwnerId(cart.getOwnerId().getOwnerId());
        entity.setUpdatedAt(cart.getUpdatedAt());


        for (CartItem item : cart.getItems()) {

            CartItemEntity existing = entity.getItems()
                    .stream()
                    .filter(e -> e.getCartItemId()
                            .equals(item.getCartItemId().cartItemId()))
                    .findFirst()
                    .orElse(null);


            if(existing != null) {
                updateCartItemEntity(existing, item);
            }
            else {
                CartItemEntity newEntity = createCartItemEntity(item);
                newEntity.setCart(entity);
                entity.getItems().add(newEntity);
            }
        }
    }

    public CartEntity createCartEntity(Cart cart) {
        CartEntity entity = new CartEntity(
                cart.getCartId().cartId(),
                cart.getOwnerId().getOwnerId()
        );

        cart.getItems().stream()
                .map(this::createCartItemEntity)
                .forEach(item -> {
                    item.setCart(entity);
                    entity.getItems().add(item);
                });


        System.out.println("DOMAIN CART ID: " + cart.getCartId().cartId());
        System.out.println("ENTITY CART ID: " + entity.getCartId());
        return entity;
    }

    public Cart createCart(CartEntity entity) {
        return new Cart(
                CartId.of(entity.getCartId()),
                SessionOwner.of(entity.getOwnerId()),
                entity.getItems().stream().map(this::createCartItem).toList(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }


    //  For the Cart Item Entity...
    public void updateCartItemEntity(CartItemEntity entity, CartItem item) {

        entity.setVariantId(item.getVariantId());
        entity.setUnitPrice(item.getUnitPrice());
        entity.setQuantity(item.getQuantity());

    }

    public CartItemEntity createCartItemEntity(CartItem item) {

        return new CartItemEntity(
                item.getVariantId(),
                item.getUnitPrice(),
                item.getQuantity()
        );
    }

    public CartItem createCartItem(CartItemEntity entity) {
        return new CartItem(
                CartItemId.of(entity.getCartItemId()),
                entity.getVariantId(),
                entity.getUnitPrice(),
                entity.getQuantity()
        );
    }

}



