package com.example.ShlopApp.Catalog.Application.internals.interactor;

import com.example.ShlopApp.Catalog.Application.api.CatalogFacade;
import com.example.ShlopApp.Catalog.Application.api.dto.ProductDto;
import com.example.ShlopApp.Catalog.Application.api.VariantDto;
import com.example.ShlopApp.Catalog.Application.internals.query.GetAllVariantsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatalogFacadeImpl implements CatalogFacade {
    private final GetAllVariantsUseCase getAllVariantsUseCase;
    private final GetVariantByIdUseCase getVariantByIdUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;


    @Override
    public List<VariantDto> getAllVariants(Long productId) {
        return getAllVariantsUseCase.execute(
                new GetAllVariantsQuery(productId));
    }

    @Override
    public VariantDto getVariantById(UUID variantId) {
        return getVariantById(variantId);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return getProductById(productId);
    }
}
