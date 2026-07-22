package com.example.ShlopApp.Commerce.Domain.Order.model;

import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OwnerId;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderId;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderStatus;
import com.example.ShlopApp.Commerce.Domain.Payment.model.ValueObjects.PaymentStatus;
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
    private OrderStatus orderStatus;
    private final Instant createdAt;
    private Instant completedAt;

    public Order(
            OwnerId ownerId,
            List<OrderLine> lineItems,
            OrderStatus orderStatus
    ) {
        this.ownerId = ownerId;
        this.lineItems = new ArrayList<>(lineItems);
        this.orderStatus = orderStatus;
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
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }


    public List<OrderLine> getLineItems() {
        return List.copyOf(lineItems);
    }

    public void changeOrderStatus(OrderStatus status) {
        this.orderStatus = status;
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

    public void assignId(OrderId orderId) {
        if (this.orderId != null) {
            throw new IllegalStateException("Order already has an ID");
        }

        this.orderId = orderId;
    }

    public void markAsCompleted() {
        this.orderStatus = OrderStatus.COMPLETED;
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
                ", orderStatus=" + orderStatus +
                ", createdAt=" + createdAt +
                '}';
    }
}


