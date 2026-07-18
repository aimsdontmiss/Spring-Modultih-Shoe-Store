package com.example.ShlopApp.Catalog.Infrastructure.adapter;

import com.example.ShlopApp.Catalog.Domain.model.Variant;
import com.example.ShlopApp.Catalog.Domain.port.VariantRepoPort;
import com.example.ShlopApp.Catalog.Infrastructure.persistence.CatalogPersistenceMapper;
import com.example.ShlopApp.Catalog.Infrastructure.persistence.VariantEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VariantRepoAdapter implements VariantRepoPort {
    private final JpaVariantRepository repository;
    private final CatalogPersistenceMapper mapper = new CatalogPersistenceMapper();

    public VariantRepoAdapter(JpaVariantRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Variant variant) {

    }

//    @Override
//    public Optional<Variant> findById(UUID variantId) {
//        Optional<VariantEntity> entity = repository.findById(variantId);
//        return entity.map(mapper::toVariantDomain);
//    }


    @Override
    public Optional<Variant> findById(UUID variantId) {

        System.out.println("SEARCHING: " + variantId);

        Optional<VariantEntity> entity = repository.findById(variantId);

        System.out.println("FOUND? " + entity.isPresent());

        entity.ifPresent(e -> {
            System.out.println("ENTITY ID: " + e.getVariantId());
            System.out.println("PRICE: " + e.getPrice());
            System.out.println("SIZE: " + e.getShoeSize());
        });

        return entity.map(mapper::toVariantDomain);
    }

    @Override
    public List<Variant> findByProductId(Long id) {
        return repository.findByProductId(id)
                .stream()
                .map(mapper::toVariantDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {

    }
}
