package io.github.akondratsky.service;

import io.github.akondratsky.entity.Sale;
import io.github.akondratsky.repository.Repository;

public class SaleService {
    private final Repository<Sale> repository;

    public SaleService(Repository<Sale> repository) {
        this.repository = repository;
    }
}
