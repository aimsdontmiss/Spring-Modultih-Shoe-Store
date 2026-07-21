package com.example.ShlopApp.Commerce.Application.internals.Order.interactor;

import com.example.ShlopApp.Cart.Application.api.CartFacade;
import com.example.ShlopApp.Cart.Application.api.CartSnapshot;
import com.example.ShlopApp.Cart.Application.api.CustomerAccess;
import com.example.ShlopApp.Commerce.Application.internals.Order.command.CheckoutCommand;
import com.example.ShlopApp.Commerce.Domain.Order.model.Order;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OwnerId;
import com.example.ShlopApp.Commerce.Domain.Order.port.OrderLineRepoPort;
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
    private final OrderRepoPort orderRepo;
    private final OrderLineRepoPort orderLineRepo;
    private final OrderPersistenceMapper mapper;
    private final CartFacade cartFacade;
    private final IdentityFacade identityFacade;

    @Transactional
    public Order execute(CheckoutCommand command) {


        Optional<AccountInfo> account = identityFacade.currentAccount();

        CartSnapshot cart = cartFacade.getCart(command.cartId());

        if (cart.owner() instanceof CustomerAccess) {
            Order order = Order.create(
                    OwnerId.of((CustomerAccess) cart.owner()),
                    cart.items()
                            .stream()
                            .map(mapper::toOrderLineFromCart)
                            .toList()
            );

            cartFacade.clearCart(command.cartId());
            orderRepo.save(order);
            return order;
        }



        Order order = Order.create(
                new OwnerId(command.ownerId()),
                cart.items()
                        .stream()
                        .map(mapper::toOrderLineFromCart)
                        .toList()
        );

        System.out.println("ORDER: " + order);

        cartFacade.clearCart(command.cartId());
        orderRepo.save(order);
        return order;
    }
}

