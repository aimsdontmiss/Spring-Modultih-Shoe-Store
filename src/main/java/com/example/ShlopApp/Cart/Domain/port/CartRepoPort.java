package com.example.ShlopApp.Cart.Domain.port;

import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Infrastructure.persistence.CartEntity;

import java.util.Optional;
import java.util.UUID;

public interface CartRepoPort {
    Cart save(Cart cart);
    Optional<Cart> findByOwnerId(CartOwner owner);

    Optional<Cart> findById(UUID cartId);
    void delete(Cart cart);
    void clearItems(UUID cartId);
}
