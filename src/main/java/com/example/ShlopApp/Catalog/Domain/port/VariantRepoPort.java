package com.example.ShlopApp.Catalog.Domain.port;

import com.example.ShlopApp.Catalog.Domain.model.Variant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VariantRepoPort {
    void save(Variant variant);
    Optional<Variant> findById(UUID variantId);
    List<Variant> findByProductId(Long id);
    void deleteById(Long id);
}



