package io.github.akondratsky.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.akondratsky.entity.Currency;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CurrencyResourceRepository extends MemoryRepository<Currency> {
    public CurrencyResourceRepository(ObjectMapper mapper) {
        URL resourceUrl = CurrencyResourceRepository.class.getResource("/currencies.json");

        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource nof file not found");
        }

        TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType currencyCollectionType = typeFactory.constructCollectionType(ArrayList.class, Currency.class);

        try {
            List<Currency> currencies = mapper.readValue(resourceUrl, currencyCollectionType);
            currencies.forEach(super::save);
        } catch (IOException e) {
            System.err.println("Unable to read currencies from file");
        }
    }

    @Override
    int getId(Currency obj) {
        return obj.getId();
    }

    @Override
    public void save(Currency obj) {
        throw new IllegalArgumentException("Currency cannot be saved");
    }
}
