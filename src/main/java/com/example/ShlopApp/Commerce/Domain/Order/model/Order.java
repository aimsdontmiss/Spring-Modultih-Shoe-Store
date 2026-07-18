package com.example.ShlopApp.Commerce.Domain.Order.model;

import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderId;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Order {
    private final OrderId orderId;
    private final UUID customerId;
    private final List<OrderLine> lineItems;
    private OrderStatus status;
    private final Instant createdAt;


    public Order(
            OrderId orderId,
            UUID customerId,
            List<OrderLine> lineItems,
            OrderStatus orderStatus
    ) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.lineItems = new ArrayList<>();
        this.status = orderStatus;
        this.createdAt = Instant.now();
    }

    public void changeStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return lineItems.stream()
                .map(OrderLine::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addLineItem(OrderLine lineItem) {
        this.lineItems.add(lineItem);
    }

    public void removeLineItem(OrderLine lineItem) {
        this.lineItems.remove(lineItem);
    }

    public List<OrderLine> getLineItems() {
        return List.copyOf(lineItems);
    }


    public static Order create(
            UUID customerId, 
            List<OrderLine> lineItems
    ) {
        return new Order(
                OrderId.create(),
                customerId,
                lineItems,
                OrderStatus.PENDING
        );
    }
}
