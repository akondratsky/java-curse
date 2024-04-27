package io.github.akondratsky.service;

import io.github.akondratsky.entity.Sale;
import io.github.akondratsky.repository.Repository;

import java.util.List;

public class SaleService {
    private final Repository<Sale> repository;

    public SaleService(Repository<Sale> repository) {
        this.repository = repository;
    }

    public List<Sale> loadAllByPersonId(int id) {
        return repository.loadAll().stream()
                .filter(sale -> sale != null && sale.getId() == id)
                .toList();
    }
}
