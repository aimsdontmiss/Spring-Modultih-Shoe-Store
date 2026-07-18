package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.api.CartFacade;
import com.example.ShlopApp.Cart.Application.api.CartItemSnapshot;
import com.example.ShlopApp.Cart.Application.api.CartSnapshot;
import com.example.ShlopApp.Cart.Application.api.dto.CartItemDto;
import com.example.ShlopApp.Cart.Application.internals.command.DeleteCartCommand;
import com.example.ShlopApp.Cart.Application.internals.query.GetCartQuery;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.CartItem;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CartFacadeImpl implements CartFacade {

    private final GetCartUseCase getCartUseCase;
    private final DeleteCartUseCase deleteCartUseCase;
    private final ClearCartUseCase clearCartUseCase;



    @Override
    public CartSnapshot getCart(UUID cartId) {
        Cart cart = getCartUseCase.execute(new GetCartQuery(cartId));
        return CartSnapshot.of(cart);
    }

    @Override
    public void clearCart(UUID cartId) {
        clearCartUseCase.execute(new DeleteCartCommand(cartId));
    }
}




