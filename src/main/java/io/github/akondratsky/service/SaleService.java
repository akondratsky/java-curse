package io.github.akondratsky.service;

import io.github.akondratsky.entity.Currency;
import io.github.akondratsky.entity.Sale;
import io.github.akondratsky.repository.Repository;

import java.util.List;
import java.util.Map;

public class SaleService {
    private final Repository<Sale> repository;
    private final CurrencyService currencyService;

    public SaleService(Repository<Sale> repository, CurrencyService currencyService) {
        this.repository = repository;
        this.currencyService = currencyService;
    }

    public List<Sale> loadAllByPersonId(int id) {
        return repository.loadAll().stream()
                .filter(sale -> {
                    if (sale == null || sale.getPerson() == null) {
                        return false;
                    }
                    return sale.getPerson().getId() == id;
                })
                .toList();
    }

    public double getAmount(Sale sale, Currency currency) {
        double totalAmount = 0;

        // iterate over currency amounts and sum
        for (Map.Entry<Currency, Double> entry : sale.getAmount().entrySet()) {
            Currency saleCurrency = entry.getKey();
            double saleAmount = entry.getValue();
            if (saleCurrency.equals(currency)) {
                totalAmount += saleAmount;
            } else {
                totalAmount += currencyService.getRate(saleCurrency, currency) * saleAmount;
            }
        }
        return totalAmount;
    }
}
