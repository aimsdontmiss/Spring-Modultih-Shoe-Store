package com.example.ShlopApp.Catalog.Infrastructure.adapter;

import com.example.ShlopApp.Catalog.Infrastructure.persistence.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface JpaVariantRepository extends JpaRepository<VariantEntity, UUID> {
    List<VariantEntity> findByProductId(Long productId);
}


