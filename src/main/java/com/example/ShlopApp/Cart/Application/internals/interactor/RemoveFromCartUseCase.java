package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.command.AddToCartCommand;
import com.example.ShlopApp.Cart.Application.internals.command.RemoveItemCommand;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import org.springframework.stereotype.Service;

@Service
public class RemoveFromCartUseCase {
    private final CartRepoPort repository;

    public RemoveFromCartUseCase(
            CartRepoPort repository
    ) {
        this.repository = repository;
    }

    public Cart execute(RemoveItemCommand command) {

        Cart cart = repository.findById(command.cartId())
                .orElseThrow();

        cart.removeFromCart(
                command.variantId(),
                command.unitPrice(),
                command.quantity()
        );

        repository.save(cart);
        return cart;
    }
}
