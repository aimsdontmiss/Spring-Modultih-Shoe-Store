package com.example.ShlopApp.Catalog.Domain.port;

import com.example.ShlopApp.Catalog.Domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepoPort {
    void save(Product shoe);
    Optional<Product> findById(Long id);
//    Optional<Product> findBySku(String sku);
    List<Product> findAll();
    void deleteById(Long id);
}
