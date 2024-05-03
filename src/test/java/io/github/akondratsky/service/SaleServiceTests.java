package io.github.akondratsky.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.akondratsky.entity.Currency;
import io.github.akondratsky.entity.Person;
import io.github.akondratsky.entity.Sale;
import io.github.akondratsky.repository.CurrencyResourceRepository;
import io.github.akondratsky.repository.MemoryRepositoryByLambda;
import io.github.akondratsky.repository.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class SaleServiceTests {
    @Test
    public void loadAllByPersonsIdFiltersByPersonId() {
        Repository<Sale> repository = new MemoryRepositoryByLambda<>(Sale::getId);
        repository.save(new Sale(1, new Person(1, "John", 25), LocalDateTime.now()));
        repository.save(new Sale(2, new Person(2, "Harry", 50), LocalDateTime.now()));

        ObjectMapper mapper = new ObjectMapper();
        Repository<Currency> currencyRepository = new CurrencyResourceRepository(mapper);
        CurrencyService currencyService = new CurrencyService(mapper, currencyRepository);
        SaleService saleService = new SaleService(repository, currencyService);
        List<Sale> sales = saleService.loadAllByPersonId(2);

        Assertions.assertEquals(1, sales.size());
    }
}
