package io.github.akondratsky.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.akondratsky.entity.Currency;
import io.github.akondratsky.repository.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class CurrencyService {
    private final HttpClient client;
    private final ObjectMapper mapper;
    private final Repository<Currency> currencyRepository;

    public CurrencyService(ObjectMapper mapper, Repository<Currency> currencyRepository) {
        this.currencyRepository = currencyRepository;
        this.mapper = mapper;
        this.client = HttpClient.newHttpClient();
    }

    public Currency getById(int id) {
        return currencyRepository.load(id);
    }

    public Currency getById(String isoName) {
        return currencyRepository.loadAll().stream()
                .filter(currency -> currency.getIsoName().equalsIgnoreCase(isoName))
                .findAny()
                .orElse(null);
    }

    public double getRate(Currency from, Currency to) {
        String isoName = from.getIsoName().toLowerCase();
        String targetIsoName = to.getIsoName().toLowerCase();
        URI uri = URI.create("https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/" +isoName + ".min.json");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            JsonNode json = mapper.readValue(response.body(), JsonNode.class);
            JsonNode rates = json.get(isoName);
            if (!rates.has(targetIsoName) || !rates.get(targetIsoName).isDouble()) {
                return -1.0;
            }
            return rates.get(targetIsoName).asDouble();
        } catch (IOException e) {
            System.err.println("Error sending request: GET " + uri);
        } catch (InterruptedException e) {
            System.err.println("Connection interrupted: GET " + uri);
        }

        return -1;
    }
}
