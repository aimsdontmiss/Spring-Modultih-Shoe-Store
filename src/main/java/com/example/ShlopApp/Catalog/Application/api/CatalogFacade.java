package com.example.ShlopApp.Catalog.Application.api;

import com.example.ShlopApp.Catalog.Application.api.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface CatalogFacade {
    List<VariantDto> getAllVariants(Long productId);
    VariantDto getVariantById(UUID variantId);
    ProductDto getProductById(Long productId);
}
