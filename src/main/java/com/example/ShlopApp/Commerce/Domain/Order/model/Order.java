package com.example.ShlopApp.Commerce.Domain.Order.model;

import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OwnerId;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderId;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private OrderId orderId;
    private final OwnerId ownerId;
    private final List<OrderLine> lineItems;
    private OrderStatus status;
    private final Instant createdAt;

    public Order(
            OwnerId ownerId,
            List<OrderLine> lineItems,
            OrderStatus orderStatus
    ) {
        this.ownerId = ownerId;
        this.lineItems = new ArrayList<>(lineItems);
        this.status = orderStatus;
        this.createdAt = Instant.now();
    }

    // For Domain rehydration from DB Entity
    public Order(
            OrderId orderId,
            OwnerId ownerId,
            List<OrderLine> lineItems,
            OrderStatus orderStatus,
            Instant createdAt
    ) {
        this.orderId = orderId;
        this.ownerId = ownerId;
        this.lineItems = new ArrayList<>();
        this.status = orderStatus;
        this.createdAt = createdAt;
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



    public void assignId(OrderId orderId) {
        if (this.orderId != null) {
            throw new IllegalStateException("Order already has an ID");
        }

        this.orderId = orderId;
    }

    public static Order create(
            OwnerId ownerId,
            List<OrderLine> lineItems
    ) {
        return new Order(
                ownerId,
                lineItems,
                OrderStatus.PENDING
        );
    }

    // For debugging purposes
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", ownerId=" + ownerId +
                ", lineItems=" + lineItems +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}


