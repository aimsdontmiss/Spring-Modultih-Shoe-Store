package com.example.ShlopApp.Cart.Application.api;

import com.example.ShlopApp.Cart.Application.api.dto.CartAccessContext;
import com.example.ShlopApp.Cart.Application.api.dto.CustomerAccess;
import com.example.ShlopApp.Cart.Application.api.dto.GuestAccess;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CustomerOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.SessionOwner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartSnapshot(
        UUID cartId,
        CartAccessContext owner,
        List<CartItemSnapshot> items,
        BigDecimal total
) {
    public static CartSnapshot of(Cart cart) {

        return new CartSnapshot(
                cart.getCartId().cartId(),
                toAccessContext(cart.getOwnerId()),
                cart.getItems().stream().map(item -> CartItemSnapshot.of(item)).toList(),
                cart.getTotalPrice()
        );
    }


    private static CartAccessContext toAccessContext(CartOwner owner) {

        if (owner instanceof CustomerOwner customerOwner) {
            return new CustomerAccess(
                    customerOwner.getOwnerId()
            );
        }

        if (owner instanceof SessionOwner sessionOwner) {
            return new GuestAccess(
                    sessionOwner.getOwnerId().toString()
            );
        }

        throw new IllegalStateException("Unknown owner type");
    }
}



