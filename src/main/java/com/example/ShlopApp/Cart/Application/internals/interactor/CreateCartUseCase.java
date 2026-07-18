package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.command.CreateCartCommand;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.CartFactory;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.SessionOwner;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import com.example.ShlopApp.Cart.Infrastructure.persistence.CartEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateCartUseCase {
    private final CartRepoPort repository;
    private final CartFactory factory;
    private final CartOwnerResolver resolver;


    public CreateCartUseCase(
            CartRepoPort repository,
            CartFactory factory,
            CartOwnerResolver resolver
    ) {
        this.repository = repository;
        this.factory = factory;
        this.resolver = resolver;
    }

    public Cart execute() {
        CartOwner owner = resolver.resolve();

        return repository.findByOwnerId(owner)
                .orElseGet(() -> {
                    Cart newCart = factory.create(owner);
                    repository.save(newCart);
                    return newCart;
                });

    }
}


