package com.example.ShlopApp.Commerce.Application.api.dto;

import com.example.ShlopApp.Commerce.Domain.Order.model.Order;
import com.example.ShlopApp.Commerce.Domain.Order.model.OrderLine;

public class OrderResponseMapper {
    public OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getOwnerId().ownerId(),
                order.getLineItems()
                        .stream()
                        .map(this::toOrderLineDto)
                        .toList()
                ,
                order.getTotal(),
                order.getOrderStatus(),
                order.getCreatedAt()
        );
    }

    public OrderLineDto toOrderLineDto(OrderLine orderLine) {
        return new OrderLineDto(
                orderLine.getProductId(),
                orderLine.getQuantity(),
                orderLine.getUnitPrice(),
                orderLine.getLineTotal()
        );
    }
}
