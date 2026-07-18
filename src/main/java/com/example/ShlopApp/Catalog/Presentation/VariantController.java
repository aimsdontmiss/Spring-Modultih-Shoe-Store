package com.example.ShlopApp.Catalog.Presentation;

import com.example.ShlopApp.Catalog.Application.api.dto.VariantDto;
import com.example.ShlopApp.Catalog.Application.internals.interactor.Variant.GetAllVariantsUseCase;
import com.example.ShlopApp.Catalog.Application.internals.interactor.Variant.GetVariantByIdUseCase;
import com.example.ShlopApp.Catalog.Application.internals.query.Variant.GetAllVariantsQuery;
import com.example.ShlopApp.Catalog.Application.internals.query.Variant.GetVariantByIdQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.util.TypeUtils.type;

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




