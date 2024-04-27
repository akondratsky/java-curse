package io.github.akondratsky.service;

import io.github.akondratsky.entity.Product;
import io.github.akondratsky.repository.Repository;

import java.util.List;

public class ProductService {
    private final Repository<Product> repository;

    public ProductService(Repository<Product> repository) {
        this.repository = repository;
    }

    public List<Product> loadAllByMaxPrice(double maxPrice) {
        return repository.loadAll().stream()
                .filter(product -> product != null && product.getPrice() < maxPrice)
                .toList();
    }

}
