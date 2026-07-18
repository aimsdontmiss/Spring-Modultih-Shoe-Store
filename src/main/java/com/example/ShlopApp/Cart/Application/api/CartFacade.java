package com.example.ShlopApp.Cart.Application.api;

import com.example.ShlopApp.Cart.Application.api.dto.CartItemDto;
import com.example.ShlopApp.Cart.Domain.model.CartItem;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;

import java.util.List;
import java.util.UUID;

public interface CartFacade {

    CartSnapshot getCart(UUID cartId);
    void clearCart(UUID cartId);

}


