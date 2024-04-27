package io.github.akondratsky;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.akondratsky.entity.Currency;
import io.github.akondratsky.repository.CurrencyResourceRepository;
import io.github.akondratsky.service.CurrencyService;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        CurrencyResourceRepository currencyRepository = new CurrencyResourceRepository(mapper);
        CurrencyService currencyService = new CurrencyService(mapper, currencyRepository);

        Currency from = currencyService.getById(42);
        Currency to = currencyService.getById("EUR");

        System.out.println(currencyService.getRate(from ,to));
    }
}