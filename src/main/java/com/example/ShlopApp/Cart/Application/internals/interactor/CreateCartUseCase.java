package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.command.CreateCartCommand;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.CartFactory;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.SessionOwner;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import org.springframework.stereotype.Service;

@Service
public class CreateCartUseCase {
    private final CartRepoPort repository;
    private final CartFactory factory;
//    private final CartOwnerResolver resolver;


    public CreateCartUseCase(
            CartRepoPort repository,
            CartFactory factory
    ) {
        this.repository = repository;
        this.factory = factory;
    }

    public Cart execute(CreateCartCommand command) {

        String owner = command.owner().getOwnerId();

        return repository.findByOwner(owner)
                .orElseGet(() -> {
                    Cart newCart = factory.create(SessionOwner.of(owner));
                    repository.save(newCart);
                    return newCart;
                });

    }
}


