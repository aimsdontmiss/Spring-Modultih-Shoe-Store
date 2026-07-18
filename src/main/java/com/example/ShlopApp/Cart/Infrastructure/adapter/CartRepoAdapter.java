package com.example.ShlopApp.Cart.Infrastructure.adapter;

import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import com.example.ShlopApp.Cart.Infrastructure.persistence.CartEntity;
import com.example.ShlopApp.Cart.Infrastructure.persistence.CartPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartRepoAdapter implements CartRepoPort {
    private final JpaCartRepository repository;
    private final CartPersistenceMapper mapper = new CartPersistenceMapper();

    public CartRepoAdapter(JpaCartRepository repository) {
        this.repository = repository;
    }


    @Override
    public Cart save(Cart cart) {

        CartEntity entity = repository.findById(cart.getCartId().cartId())
                .orElseGet(() -> mapper.createCartEntity(cart));

        mapper.updateCartEntity(cart, entity);
        repository.save(entity);
        return cart;
    }

//    @Override
//    public Optional<Cart> findByOwnerId(CartOwner ownerId) {
//        Optional<CartEntity> entity = repository.findByOwner(ownerId.toString());
//        return Optional.ofNullable(mapper.createCart(entity.orElse(null)));
//    }

    @Override
    public Optional<Cart> findByOwnerId(CartOwner ownerId) {
        return repository.findByOwnerId(ownerId.getOwnerId())
                .map(mapper::createCart);
    }

    @Override
    public Optional<Cart> findById(UUID cartId) {
        return repository.findById(cartId)
                .map(mapper::createCart);
    }

    @Override
    public void delete(Cart cart) {
        repository.delete(mapper.createCartEntity(cart));
    }

    @Override
    public void clearItems(UUID cartId) {
        Cart cart = findById(cartId).orElseThrow();

        if (cart.getItems().isEmpty()) {
            return;
        }
        cart.clearCart();
        save(cart);
    }
}







