package com.example.ShlopApp.Commerce.Application.internals.Order.interactor;

import com.example.ShlopApp.Cart.Application.api.CartFacade;
import com.example.ShlopApp.Cart.Application.api.CartSnapshot;
import com.example.ShlopApp.Commerce.Application.internals.Order.command.CheckoutCommand;
import com.example.ShlopApp.Commerce.Domain.Order.exception.UnauthorizedCheckoutException;
import com.example.ShlopApp.Commerce.Domain.Order.model.Order;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderId;
import com.example.ShlopApp.Commerce.Domain.Order.port.OrderRepoPort;
import com.example.ShlopApp.Commerce.Infrastructure.persistence.OrderPersistenceMapper;
import com.example.ShlopApp.Identity.Application.api.AccountInfo;
import com.example.ShlopApp.Identity.Application.api.IdentityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckoutUseCase {
    private final OrderRepoPort repository;
    private final OrderPersistenceMapper mapper;
    private final CartFacade cartFacade;
    private final IdentityFacade identityFacade;

//    @Transactional
//    public CartSnapshot execute(CheckoutCommand command) {
//
//        Optional<AccountInfo> acc = identityFacade.currentAccount();
//        if ()
//        return cartFacade.getCart(command.cartId());
//
//    }

    @Transactional
    public OrderId execute(CheckoutCommand command) {

        AccountInfo account = identityFacade.currentAccount()
                .orElseThrow(() ->
                        new UnauthorizedCheckoutException("User does not own cart")
                );

        CartSnapshot cart = cartFacade.getCart(command.cartId());

        Order order = Order.create(
                account.accountId(),
                cart.items()
                        .stream()
                        .map(mapper::toOrderLine)
                        .toList()
        );

        repository.save(order);

        cartFacade.clearCart(command.cartId());

        return order.getOrderId();
    }
}

