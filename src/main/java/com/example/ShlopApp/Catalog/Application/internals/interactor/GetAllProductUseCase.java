package com.example.ShlopApp.Catalog.Application.internals.interactor;

import com.example.ShlopApp.Catalog.Application.api.dto.ProductDto;
import com.example.ShlopApp.Catalog.Application.api.dto.CatalogResponseMapper;
import com.example.ShlopApp.Catalog.Domain.model.Product;
import com.example.ShlopApp.Catalog.Domain.port.ProductRepoPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductUseCase {
    private final ProductRepoPort repository;
    private final CatalogResponseMapper mapper;

    public GetAllProductUseCase(
            ProductRepoPort repository,
            CatalogResponseMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProductDto> execute() {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(mapper::toProductDto)
                .toList();
    }

}
