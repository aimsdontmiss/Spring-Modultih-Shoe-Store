package com.example.ShlopApp.Commerce.Domain.Order.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class OrderLine {
    private UUID lineId;
    private final UUID productId;
    private final int quantity;
    private final BigDecimal unitPrice;

    public OrderLine(
            UUID productId,
            int quantity,
            BigDecimal unitPrice
    ) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

}


