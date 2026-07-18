package com.example.ShlopApp.Catalog.Infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "STG_PRODUCTS")
@Entity
@Getter
@NoArgsConstructor
public class ProductEntity {
    @Id
    private Long id;

    private String sku;
    private String name;
    private String brand;
    private String model;
    private String colorway;

    @Column(length = 2000)
    private String description;

    private String imageUrl;

    public ProductEntity(
            Long id,
            String sku,
            String name,
            String brand,
            String model,
            String colorway,
            String description,
            String imageUrl
    ) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.colorway = colorway;
        this.description = description;
        this.imageUrl = imageUrl;
    }


}
