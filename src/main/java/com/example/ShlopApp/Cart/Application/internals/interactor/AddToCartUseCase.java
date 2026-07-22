package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.command.AddToCartCommand;
import com.example.ShlopApp.Cart.Domain.exception.UnauthorizedCartAccessException;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import com.example.ShlopApp.Catalog.Application.api.CatalogFacade;
import com.example.ShlopApp.Catalog.Application.api.VariantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddToCartUseCase {
    private final CartRepoPort cartRepo;
    private  final CartOwnerResolver resolver;


    public Cart execute(AddToCartCommand command) {

        CartOwner owner = resolver.resolve();

        System.out.println(owner);

        Cart cart = cartRepo.findById(command.cartId())
                .orElseThrow();

//
//        System.out.println("CART OWNER: " + cart.getOwnerId());
//        System.out.println("REQUEST OWNER: " + owner);
//        System.out.println("EQUALS: " + cart.getOwnerId().equals(owner));

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

        cartRepo.save(cart);
        return cart;
    }
}



