package com.example.ShlopApp.Catalog.Application.internals.interactor.Variant;

import com.example.ShlopApp.Catalog.Application.api.dto.CatalogResponseMapper;
import com.example.ShlopApp.Catalog.Application.api.dto.VariantDto;
import com.example.ShlopApp.Catalog.Application.internals.query.Variant.GetAllVariantsQuery;
import com.example.ShlopApp.Catalog.Domain.model.Variant;
import com.example.ShlopApp.Catalog.Domain.port.VariantRepoPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllVariantsUseCase {
    private final VariantRepoPort repository;
    private final CatalogResponseMapper mapper;

    public GetAllVariantsUseCase(VariantRepoPort repository, CatalogResponseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<VariantDto> execute(GetAllVariantsQuery query) {
        List<Variant> variants = repository.findByProductId(query.productId());
        return variants.stream()
                .map(mapper::toVariantDto)
                .toList();
    }
}
