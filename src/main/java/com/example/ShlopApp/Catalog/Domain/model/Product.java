package com.example.ShlopApp.Catalog.Domain.model;

import lombok.Getter;

@Getter
public class Product {
    private Long id;
    private String sku;
    private String name;
    private String brand;
    private String Model;
    private String colorway;
    private String description;
    private String imageUrl;

    public Product(
            Long id,
            String sku,
            String name,
            String brand,
            String Model,
            String colorway,
            String description,
            String imageUrl
    ) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.brand = brand;
        this.Model = Model;
        this.colorway = colorway;
        this.description = description;
        this.imageUrl = imageUrl;
    }

}
