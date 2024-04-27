package io.github.akondratsky.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.akondratsky.entity.Currency;
import io.github.akondratsky.repository.CurrencyResourceRepository;
import io.github.akondratsky.repository.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyService {
    private final Repository<Currency> currencyRepository;
    private final Map<Currency, Map<Currency, Double>> rates;

    public CurrencyService(ObjectMapper mapper, Repository<Currency> currencyRepository) {
        this.currencyRepository = currencyRepository;
        this.rates = new HashMap<>();

        URL resourceUrl = CurrencyResourceRepository.class.getResource("/currencies_rates.json");

        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource file not found");
        }
        try {
            JsonNode node = mapper.readValue(resourceUrl, JsonNode.class);
            node.fieldNames().forEachRemaining(currencyId -> {
                Currency currentCurrency = getById(Integer.parseInt(currencyId));
                Map<Currency, Double> currencyRates = new HashMap<>();
                JsonNode currencyRatesNode = node.get(currencyId);
                currencyRatesNode.fieldNames().forEachRemaining(currencyRateId -> {
                    Currency rateCurrency = getById(Integer.parseInt(currencyRateId));
                    currencyRates.put(rateCurrency, currencyRatesNode.get(currencyRateId).asDouble());
                });
                rates.put(currentCurrency, currencyRates);
            });
        } catch (IOException e) {
            System.err.println("Unable to read currencies from file");
        }
    }

    public Currency getById(int id) {
        return currencyRepository.load(id);
    }

    public Currency getById(String isoName) {
        return currencyRepository.loadAll().stream()
                .filter(currency -> currency.getIsoName().equals(isoName))
                .findAny()
                .orElse(null);
    }

    public double getRate(Currency from, Currency to) {
        if (!rates.containsKey(from)) {
            return -1.0;
        }
        Map<Currency, Double> currencyRates = rates.get(from);
        if (!currencyRates.containsKey(to)) {
            return -1.0;
        }
        return currencyRates.get(to);
    }
}
