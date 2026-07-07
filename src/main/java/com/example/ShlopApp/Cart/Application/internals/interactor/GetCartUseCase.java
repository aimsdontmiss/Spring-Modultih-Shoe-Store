package com.example.ShlopApp.Cart.Application.internals.interactor;

import com.example.ShlopApp.Cart.Application.internals.query.GetCartQuery;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.port.CartRepoPort;
import org.springframework.stereotype.Service;

@Service
public class GetCartUseCase {
    private final CartRepoPort repository;

    public GetCartUseCase(CartRepoPort repository) {
        this.repository = repository;
    }

    public Cart execute(GetCartQuery query) {
        return repository.findById(query.cartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
