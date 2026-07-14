package com.example.ShlopApp.Cart.Infrastructure.adapter;

import com.example.ShlopApp.Cart.Domain.model.Cart;
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
    public void save(Cart cart) {

        CartEntity entity = repository.findById(cart.getCartId().cartId())
                .orElseGet(() -> mapper.createCartEntity(cart));

        mapper.updateCartEntity(cart, entity);

        repository.save(entity);
    }

    @Override
    public Optional<Cart> findByOwner(String ownerId) {
        return repository.findByOwner(ownerId)
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
}







