package com.example.ShlopApp.Catalog.Infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Table(name = "STG_VARIANTS")
@NoArgsConstructor
public class VariantEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "VARIANT_ID")
    private UUID variantId;
    private Long productId;

    private String shoeSize;
    private BigDecimal price;
    private String currency;
    private String market;
    private boolean available;

    public VariantEntity(
            Long productId,
            String shoeSize,
            BigDecimal price,
            String currency,
            String market,
            boolean available
    ) {
        this.productId = productId;
        this.shoeSize = shoeSize;
        this.price = price;
        this.currency = currency;
        this.market = market;
        this.available = available;
    }
}
