package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.command.DeleteCartCommand;
import com.example.ShlopApp.Cart.Domain.exception.CartNotFoundException;
import com.example.ShlopApp.Cart.Domain.exception.UnauthorizedCartAccessException;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import org.springframework.stereotype.Service;


@Service
public class DeleteCartUseCase {
    private final CartRepoPort repository;
    private final CartOwnerResolver resolver;

    public DeleteCartUseCase(
            CartRepoPort repository,
            CartOwnerResolver resolver
    ) {
        this.repository = repository;
        this.resolver = resolver;
    }

    public void execute(DeleteCartCommand command) {

        CartOwner owner = resolver.resolve();
        CartId cartId = CartId.of(command.cartId());

        Cart cart = repository.findById(command.cartId())
                .orElseThrow(() -> new CartNotFoundException(cartId));
//
//        if (!cart.belongsTo(owner)) {
//            throw new UnauthorizedCartAccessException();
//        }

        if (!cart.getOwnerId().getOwnerId()
                .equals(owner.getOwnerId())) {
            throw new UnauthorizedCartAccessException();
        }


        repository.delete(cart);
    }
}
