package com.example.ShlopApp.Commerce.Infrastructure.persistence;

import com.example.ShlopApp.Cart.Application.api.CartFacade;
import com.example.ShlopApp.Cart.Application.api.CartItemSnapshot;
import com.example.ShlopApp.Catalog.Application.api.CatalogFacade;
import com.example.ShlopApp.Catalog.Application.api.VariantDto;
import com.example.ShlopApp.Commerce.Domain.Order.model.Order;
import com.example.ShlopApp.Commerce.Domain.Order.model.OrderLine;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OwnerId;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class OrderPersistenceMapper {

    public OrderEntity toOrderEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity(
                order.getOwnerId().ownerId()
        );

        order.getLineItems()
                .forEach(line -> {
                    OrderLineEntity lineEntity = toOrderLineEntity(line);
                    orderEntity.addLine(lineEntity);
                });

        return orderEntity;
    }

    public Order toOrderDomain(OrderEntity entity) {
        return new Order(
                new OwnerId(entity.getOwnerId()),
                entity.getLineItems().stream().map(this::toOrderLineDomain).toList(),
                OrderStatus.PENDING
        );
    }

    public OrderLine toOrderLineDomain(OrderLineEntity entity) {
        return new OrderLine(
                entity.getProductId(),
                entity.getQuantity(),
                entity.getUnitPrice()
        );
    }

    public OrderLineEntity toOrderLineEntity(OrderLine orderLine) {
        return new OrderLineEntity(
                orderLine.getProductId(),
                orderLine.getQuantity(),
                orderLine.getUnitPrice()
        );
    }

    public OrderLine toOrderLineFromCart(CartItemSnapshot item) {

        return new OrderLine(
                item.variantId(),
                item.quantity(),
                item.unitPrice()
        );
    }

}




