package com.example.ShlopApp.Catalog.Application.api.dto;

public record ProductDto(
        Long id,
        String sku,
        String name,
        String brand,
        String model,
        String colorway,
        String description,
        String imageUrl
) {}
