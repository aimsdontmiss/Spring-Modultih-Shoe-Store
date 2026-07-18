package com.example.ShlopApp.Catalog.Application.internals.interactor.Variant;

import com.example.ShlopApp.Catalog.Application.api.dto.CatalogResponseMapper;
import com.example.ShlopApp.Catalog.Application.api.dto.VariantDto;
import com.example.ShlopApp.Catalog.Application.internals.query.Variant.GetVariantByIdQuery;
import com.example.ShlopApp.Catalog.Domain.model.Variant;
import com.example.ShlopApp.Catalog.Infrastructure.adapter.VariantRepoAdapter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetVariantByIdUseCase {
    private final VariantRepoAdapter repository;
    private final CatalogResponseMapper mapper;

    public GetVariantByIdUseCase(
            VariantRepoAdapter repository,
            CatalogResponseMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<VariantDto> execute(GetVariantByIdQuery query) {
        Optional<Variant> variant = repository.findById(query.variantId());
        return variant.map(mapper::toVariantDto);
    }
}
