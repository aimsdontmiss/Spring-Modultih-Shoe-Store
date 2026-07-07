package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.command.CreateCartCommand;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.OwnerId;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
public class CreateCartUseCase {
    private final CartRepoPort cartRepoPort;

    public CreateCartUseCase(CartRepoPort cartRepoPort) {
        this.cartRepoPort = cartRepoPort;
    }

    public Cart execute(CreateCartCommand command) {

        String owner = command.owner().ownerId();
        System.out.println("UseCase owner = " + owner);
        return cartRepoPort.findByOwner(owner)
                .orElseGet(() -> {
                    Cart newCart = Cart.create(OwnerId.of(owner));

                    cartRepoPort.save(newCart);
                    return newCart;
                });
    }
}


