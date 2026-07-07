package com.example.ShlopApp.Cart.Domain.port;

import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.OwnerId;

import java.util.Optional;
import java.util.UUID;

public interface CartRepoPort {
    void save(Cart cart);
    Optional<Cart> findByOwner(String owner);

    Optional<Cart> findById(UUID cartId);
    void delete(Cart cart);
}
