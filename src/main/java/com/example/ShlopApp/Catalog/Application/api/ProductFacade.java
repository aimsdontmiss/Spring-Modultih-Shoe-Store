package com.example.ShlopApp.Catalog.Application.api;

import com.example.ShlopApp.Catalog.Application.internals.interactor.Product.GetAllProductUseCase;
import com.example.ShlopApp.Catalog.Application.internals.interactor.Product.GetProductByIdUseCase;

public class ProductFacade {
    private final GetAllProductUseCase getAllProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;

    public ProductFacade(
            GetAllProductUseCase getAllProductUseCase,
            GetProductByIdUseCase getProductByIdUseCase
    ) {
        this.getAllProductUseCase = getAllProductUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
    }
}
