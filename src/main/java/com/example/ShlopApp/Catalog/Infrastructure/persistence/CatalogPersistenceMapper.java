package com.example.ShlopApp.Catalog.Infrastructure.persistence;

import com.example.ShlopApp.Catalog.Domain.model.Product;
import com.example.ShlopApp.Catalog.Domain.model.Variant;
import org.springframework.stereotype.Component;

@Component
public class CatalogPersistenceMapper {

    //  For the Product Entity...
    public ProductEntity toProductEntity(Product product) {
        return new ProductEntity(
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

    public Product toProductDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getSku(),
                entity.getName(),
                entity.getBrand(),
                entity.getModel(),
                entity.getColorway(),
                entity.getDescription(),
                entity.getImageUrl()
        );
    }

    //  For the Variant Entity...
    public VariantEntity toVariantEntity(Variant variant) {
        return new VariantEntity(
                variant.getProductId(),
                variant.getShoeSize(),
                variant.getPrice(),
                variant.getCurrency(),
                variant.getMarket(),
                variant.isAvailable()
        );
    }

    public Variant toVariantDomain(VariantEntity entity) {
        return new Variant(
                entity.getVariantId(),
                entity.getProductId(),
                entity.getShoeSize(),
                entity.getPrice(),
                entity.getCurrency(),
                entity.getMarket(),
                entity.isAvailable()
        );
    }

}
