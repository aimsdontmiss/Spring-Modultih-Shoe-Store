package com.example.ShlopApp.Catalog.Application.internals.interactor.Product;

import com.example.ShlopApp.Catalog.Application.api.dto.ProductDto;
import com.example.ShlopApp.Catalog.Application.api.dto.CatalogResponseMapper;
import com.example.ShlopApp.Catalog.Application.internals.query.Product.GetProductByIdQuery;
import com.example.ShlopApp.Catalog.Domain.model.Product;
import com.example.ShlopApp.Catalog.Domain.port.ProductRepoPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductByIdUseCase {
    private final ProductRepoPort repository;
    private final CatalogResponseMapper mapper;

    public GetProductByIdUseCase(
            ProductRepoPort repository,
            CatalogResponseMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<ProductDto> execute(GetProductByIdQuery query) {
        Optional<Product> product = repository.findById(query.id());
        return product.map(mapper::toProductDto);
    }
}
