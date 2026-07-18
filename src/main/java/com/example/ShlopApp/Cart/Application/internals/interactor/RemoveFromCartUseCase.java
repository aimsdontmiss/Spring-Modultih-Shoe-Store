package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.command.AddToCartCommand;
import com.example.ShlopApp.Cart.Application.internals.command.RemoveItemCommand;
import com.example.ShlopApp.Cart.Domain.exception.UnauthorizedCartAccessException;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import org.springframework.stereotype.Service;

@Service
public class RemoveFromCartUseCase {
    private final CartRepoPort repository;
    private final CartOwnerResolver resolver;

    public RemoveFromCartUseCase(
            CartRepoPort repository,
            CartOwnerResolver resolver
    ) {
        this.repository = repository;
        this.resolver = resolver;
    }

    public Cart execute(RemoveItemCommand command) {
        CartOwner owner = resolver.resolve();

        Cart cart = repository.findById(command.cartId())
                .orElseThrow();

//        if (!cart.belongsTo(owner)) {
//            throw new UnauthorizedCartAccessException();
//        }

        if (!cart.getOwnerId().getOwnerId()
            .equals(owner.getOwnerId())) {
                throw new UnauthorizedCartAccessException();
        }

        cart.removeFromCart(
                command.variantId(),
                command.unitPrice(),
                command.quantity()
        );
        cart.touch();

        repository.save(cart);
        return cart;
    }
}
