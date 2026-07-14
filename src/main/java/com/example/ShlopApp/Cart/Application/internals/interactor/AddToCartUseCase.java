package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.api.dto.CartResponseMapper;
import com.example.ShlopApp.Cart.Application.internals.command.AddToCartCommand;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.CartItem;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartItemId;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import com.example.ShlopApp.Catalog.Domain.model.Variant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddToCartUseCase {
    private final CartRepoPort repository;

    public AddToCartUseCase(
            CartRepoPort repository
    ) {
        this.repository = repository;
    }

    public Cart execute(AddToCartCommand command) {

        Cart cart = repository.findById(command.cartId())
                .orElseThrow();

        cart.addToCart(
                command.variantId(),
                command.unitPrice(),
                command.quantity()
        );

        repository.save(cart);
        return cart;
    }
}



