package com.example.ShlopApp.Commerce.Infrastructure.persistence;

import com.example.ShlopApp.Cart.Application.api.CartItemSnapshot;
import com.example.ShlopApp.Commerce.Domain.Order.model.Order;
import com.example.ShlopApp.Commerce.Domain.Order.model.OrderLine;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderId;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderPersistenceMapper {
    public OrderEntity toOrderEntity(Order order) {
        return new OrderEntity(
                order.getCustomerId()
        );
    }

    public Order toOrderDomain(OrderEntity entity) {
        return new Order(
                OrderId.of(entity.getOrderId()),
                entity.getCustomerId(),
                entity.getLineItems().stream().map(this::toOrderItemDomain).toList(),
                OrderStatus.PENDING
        );
    }

    public OrderLine toOrderItemDomain(OrderLineEntity entity) {
        return new OrderLine(
                entity.getProductId(),
                entity.getQuantity(),
                entity.getUnitPrice()
        );
    }
    public OrderLine toOrderLine(CartItemSnapshot item) {

        return new OrderLine(
                item.variantId(),
                item.quantity(),
                item.unitPrice()
        );
    }

}
