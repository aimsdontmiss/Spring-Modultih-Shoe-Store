package com.example.ShlopApp.Catalog.Presentation;

import com.example.ShlopApp.Catalog.Application.api.dto.ProductDto;
import com.example.ShlopApp.Catalog.Application.api.VariantDto;
import com.example.ShlopApp.Catalog.Application.internals.interactor.GetAllProductUseCase;
import com.example.ShlopApp.Catalog.Application.internals.interactor.GetProductByIdUseCase;
import com.example.ShlopApp.Catalog.Application.internals.interactor.GetAllVariantsUseCase;
import com.example.ShlopApp.Catalog.Application.internals.query.GetProductByIdQuery;
import com.example.ShlopApp.Catalog.Application.internals.query.GetAllVariantsQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
class ProductController {
    private final GetAllProductUseCase getAllProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final GetAllVariantsUseCase getAllVariantsUseCase;


    public ProductController(
            GetAllProductUseCase getAllProductUseCase,
            GetProductByIdUseCase getProductByIdUseCase,
            GetAllVariantsUseCase getAllVariantsUseCase
    ) {
        this.getAllProductUseCase = getAllProductUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.getAllVariantsUseCase = getAllVariantsUseCase;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return getAllProductUseCase.execute();
    }

    @GetMapping("/{id}")
    public Optional<ProductDto> getProductById(@PathVariable Long id) {
        return getProductByIdUseCase.execute(new GetProductByIdQuery(id));
    }

    @GetMapping("/{productId}/all")
    public List<VariantDto> getProductVariants(@PathVariable Long productId) {
        return getAllVariantsUseCase.execute(new GetAllVariantsQuery(productId));
    }

}



