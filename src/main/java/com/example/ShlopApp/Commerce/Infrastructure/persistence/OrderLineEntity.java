package com.example.ShlopApp.Commerce.Infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Table(name = "STG_ORDER_LINES")
@NoArgsConstructor
public class OrderLineEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private UUID lineId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private UUID productId;
    private int quantity;
    private BigDecimal unitPrice;

    public OrderLineEntity(
            UUID productId,
            int quantity,
            BigDecimal unitPrice
    ) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
