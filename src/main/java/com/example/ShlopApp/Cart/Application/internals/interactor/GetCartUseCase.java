package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.query.GetCartQuery;
import com.example.ShlopApp.Cart.Domain.exception.CartNotFoundException;
import com.example.ShlopApp.Cart.Domain.exception.UnauthorizedCartAccessException;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import org.springframework.stereotype.Service;

@Service
public class GetCartUseCase {
    private final CartRepoPort repository;
    private final CartOwnerResolver resolver;

    public GetCartUseCase(
            CartRepoPort repository,
            CartOwnerResolver resolver
    ) {
        this.repository = repository;
        this.resolver = resolver;
    }

    public Cart execute(GetCartQuery query) {
        CartOwner owner = resolver.resolve();

        Cart cart = repository.findById(query.cartId())
                .orElseThrow(() -> new CartNotFoundException(CartId.of(query.cartId())));

//        if (!cart.belongsTo(owner)) {
//            throw new UnauthorizedCartAccessException();
//        }

        if (!cart.getOwnerId().getOwnerId()
                .equals(owner.getOwnerId())) {
            throw new UnauthorizedCartAccessException();
        }

        return cart;
    }
}
