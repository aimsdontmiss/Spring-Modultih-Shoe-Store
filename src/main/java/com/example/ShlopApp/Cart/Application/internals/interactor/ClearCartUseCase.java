package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.command.DeleteCartCommand;
import com.example.ShlopApp.Cart.Domain.exception.CartNotFoundException;
import com.example.ShlopApp.Cart.Domain.exception.UnauthorizedCartAccessException;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClearCartUseCase {
    private final CartRepoPort repository;
    private final CartOwnerResolver resolver;

    public void execute(DeleteCartCommand command) {

        CartOwner owner = resolver.resolve();

        Cart cart = repository.findById(command.cartId())
                .orElseThrow(() ->
                        new CartNotFoundException(CartId.of(command.cartId()))
                );

        if (!cart.getOwnerId().getOwnerId()
                .equals(owner.getOwnerId())) {
            throw new UnauthorizedCartAccessException();
        }

        repository.clearItems(cart.getCartId().cartId());
    }
}
