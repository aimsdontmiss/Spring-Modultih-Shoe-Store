package com.example.ShlopApp.Catalog.Infrastructure.adapter;

import com.example.ShlopApp.Catalog.Infrastructure.persistence.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
