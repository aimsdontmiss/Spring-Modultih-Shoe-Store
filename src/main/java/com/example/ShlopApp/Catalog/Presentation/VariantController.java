package com.example.ShlopApp.Catalog.Presentation;

import com.example.ShlopApp.Catalog.Application.api.VariantDto;
import com.example.ShlopApp.Catalog.Application.internals.interactor.GetVariantByIdUseCase;
import com.example.ShlopApp.Catalog.Application.internals.query.GetVariantByIdQuery;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/variants")
class VariantController {
    private final GetVariantByIdUseCase getVariantByIdUseCase;

    public VariantController(
            GetVariantByIdUseCase getVariantByIdUseCase
    ) {
        this.getVariantByIdUseCase = getVariantByIdUseCase;
    }

    @GetMapping("/{variantId}")
    public Optional<VariantDto> getVariantById(@PathVariable UUID variantId) {
        System.out.println(variantId.getClass());
        return getVariantByIdUseCase.execute(new GetVariantByIdQuery(variantId));
    }
}




