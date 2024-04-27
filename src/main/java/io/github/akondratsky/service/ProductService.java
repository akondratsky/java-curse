package io.github.akondratsky.service;

import io.github.akondratsky.entity.Product;
import io.github.akondratsky.repository.Repository;

public class ProductService {
    private final Repository<Product> repository;

    public ProductService(Repository<Product> repository) {
        this.repository = repository;
    }
}
