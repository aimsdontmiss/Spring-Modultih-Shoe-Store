package com.example.ShlopApp.Catalog.Domain.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Variant {
    private UUID variantId;
    private Long productId;
    private String shoeSize;
    private BigDecimal price;
    private String currency;
    private String market;
    private boolean available;

    public Variant(
            UUID variantId,
            Long productId,
            String shoeSize,
            BigDecimal price,
            String currency,
            String market,
            boolean available
    ) {
        this.variantId = variantId;
        this.productId = productId;
        this.shoeSize = shoeSize;
        this.price = price;
        this.currency = currency;
        this.market = market;
        this.available = available;
    }

}



