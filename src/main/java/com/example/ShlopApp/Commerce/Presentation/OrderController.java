package com.example.ShlopApp.Commerce.Presentation;

import com.example.ShlopApp.Cart.Application.api.CartSnapshot;
import com.example.ShlopApp.Commerce.Application.internals.Order.command.CheckoutCommand;
import com.example.ShlopApp.Commerce.Application.internals.Order.interactor.CheckoutUseCase;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/order")
class OrderController {
    private final CheckoutUseCase checkoutUseCase;

    public OrderController(CheckoutUseCase checkoutUseCase) {
        this.checkoutUseCase = checkoutUseCase;
    }

    @PostMapping("/checkout")
    public ResponseEntity<OrderId> checkout(@RequestBody CheckoutCommand command) {
        return ResponseEntity.ok(checkoutUseCase.execute(command));
    }
}
