package com.example.ShlopApp.Commerce.Infrastructure.persistence;

import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "STG_ORDERS")
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;
    private UUID customerId;
    private String status;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderLineEntity> lineItems = new ArrayList<>();


    public OrderEntity(
            UUID customerId
    ) {
        this.customerId = customerId;
        this.lineItems = new ArrayList<>();
        this.status = OrderStatus.PENDING.name();
    }

    public void addLine(OrderLineEntity line) {
        lineItems.add(line);
        line.setOrder(this);
    }

    public void removeLine(OrderLineEntity line) {
        lineItems.remove(line);
        line.setOrder(null);
    }

}
