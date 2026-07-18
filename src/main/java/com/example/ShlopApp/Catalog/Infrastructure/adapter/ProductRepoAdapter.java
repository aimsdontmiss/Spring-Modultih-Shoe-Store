package com.example.ShlopApp.Catalog.Infrastructure.adapter;

import com.example.ShlopApp.Catalog.Domain.model.Product;
import com.example.ShlopApp.Catalog.Domain.port.ProductRepoPort;
import com.example.ShlopApp.Catalog.Infrastructure.persistence.CatalogPersistenceMapper;
import com.example.ShlopApp.Catalog.Infrastructure.persistence.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductRepoAdapter implements ProductRepoPort {

    private final JpaProductRepository repository;
    private final CatalogPersistenceMapper mapper = new CatalogPersistenceMapper();

    public ProductRepoAdapter(JpaProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Product shoe) {
        ProductEntity entity = mapper.toProductEntity(shoe);
        repository.save(entity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<ProductEntity> entity = repository.findById(id);
        return entity.map(mapper::toProductDomain);
    }

//    @Override
//    public Optional<Product> findBySku(String sku) {
//        return Optional.empty(repository.);
//    }

    @Override
    public List<Product> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
