package com.example.ShlopApp.Catalog.Application.api;

import com.example.ShlopApp.Catalog.Application.internals.interactor.Variant.GetAllVariantsUseCase;
import com.example.ShlopApp.Catalog.Application.internals.interactor.Variant.GetVariantByIdUseCase;

public class VariantFacade {
    private final GetAllVariantsUseCase getAllVariantsUseCase;
    private final GetVariantByIdUseCase getVariantByIdUseCase;

    public VariantFacade(
            GetAllVariantsUseCase getAllVariantsUseCase,
            GetVariantByIdUseCase getVariantByIdUseCase
    ) {
        this.getAllVariantsUseCase = getAllVariantsUseCase;
        this.getVariantByIdUseCase = getVariantByIdUseCase;
    }
}
