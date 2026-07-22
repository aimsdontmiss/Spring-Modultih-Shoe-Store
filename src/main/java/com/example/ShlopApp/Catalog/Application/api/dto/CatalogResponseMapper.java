package com.example.ShlopApp.Catalog.Application.api.dto;

import com.example.ShlopApp.Catalog.Application.api.VariantDto;
import com.example.ShlopApp.Catalog.Domain.model.Product;
import com.example.ShlopApp.Catalog.Domain.model.Variant;
import org.springframework.stereotype.Component;

@Component
public class CatalogResponseMapper {
    //    For the Product model...
    public ProductDto toProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getBrand(),
                product.getModel(),
                product.getColorway(),
                product.getDescription(),
                product.getImageUrl()
        );
    }

    public Product toProductDomain(ProductDto dto) {
        return new Product(
                dto.id(),
                dto.sku(),
                dto.name(),
                dto.brand(),
                dto.model(),
                dto.colorway(),
                dto.description(),
                dto.imageUrl()
        );
    }


    //    For the Variant model...
    public VariantDto toVariantDto(Variant variant) {
        return new VariantDto(
                variant.getVariantId(),
                variant.getProductId(),
                variant.getShoeSize(),
                variant.getPrice(),
                variant.getCurrency(),
                variant.getMarket(),
                variant.isAvailable()
        );
    }

    public Variant toVariantDomain(VariantDto dto) {
        return new Variant(
                dto.variantId(),
                dto.productId(),
                dto.shoeSize(),
                dto.price(),
                dto.currency(),
                dto.market(),
                dto.available()
        );
    }
}
