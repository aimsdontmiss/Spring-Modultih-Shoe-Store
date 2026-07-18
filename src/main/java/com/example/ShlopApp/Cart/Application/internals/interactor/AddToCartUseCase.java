package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.command.AddToCartCommand;
import com.example.ShlopApp.Cart.Domain.exception.UnauthorizedCartAccessException;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import org.springframework.stereotype.Service;

@Service
public class AddToCartUseCase {
    private final CartRepoPort repository;
    private  final CartOwnerResolver resolver;

    public AddToCartUseCase(
            CartRepoPort repository,
            CartOwnerResolver resolver
    ) {
        this.repository = repository;
        this.resolver = resolver;
    }

    public Cart execute(AddToCartCommand command) {

        CartOwner owner = resolver.resolve();

        System.out.println(owner);

        Cart cart = repository.findById(command.cartId())
                .orElseThrow();


        System.out.println("CART OWNER: " + cart.getOwnerId());
        System.out.println("REQUEST OWNER: " + owner);
        System.out.println("EQUALS: " + cart.getOwnerId().equals(owner));

//        if (!cart.belongsTo(owner)) {
//            throw new UnauthorizedCartAccessException();
//        }

        if (!cart.getOwnerId().getOwnerId()
                .equals(owner.getOwnerId())) {
            throw new UnauthorizedCartAccessException();
        }

        cart.addToCart(
                command.variantId(),
                command.unitPrice(),
                command.quantity()
        );
        cart.touch();

        repository.save(cart);
        return cart;
    }
}



