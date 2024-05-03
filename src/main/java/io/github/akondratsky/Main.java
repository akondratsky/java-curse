package io.github.akondratsky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.net.httpserver.HttpServer;
import io.github.akondratsky.entity.Currency;
import io.github.akondratsky.entity.Sale;
import io.github.akondratsky.repository.CurrencyResourceRepository;
import io.github.akondratsky.repository.DirectorySaleRepository;
import io.github.akondratsky.repository.Repository;
import io.github.akondratsky.service.CurrencyService;
import io.github.akondratsky.service.SaleService;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        File salesDir = new File("sales");

        Repository<Sale> saleRepository = new DirectorySaleRepository(salesDir);
        CurrencyResourceRepository currencyRepository = new CurrencyResourceRepository(mapper);
        CurrencyService currencyService = new CurrencyService(mapper, currencyRepository);
        SaleService saleService = new SaleService(saleRepository, currencyService);

        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(3000), 0);
        } catch (IOException e) {
            System.err.println("Error creating HTTP server");
            System.exit(1);
            return;
        }

        server.createContext("/currency", (exchange) -> {
            try (exchange) {
                String method = exchange.getRequestMethod();

                if (method.equals("GET")) {
                    System.out.println("GET /currency");
                    String query = exchange.getRequestURI().getQuery();

                    if (StringUtils.isEmpty(query)) {
                        exchange.sendResponseHeaders(400, 0);
                        return;
                    }

                    Optional<String> isoNameOptional = Arrays.stream(query.split("&")).map(param -> param.split("=")).filter(paramKeyValue -> paramKeyValue[0].equalsIgnoreCase("isoName")).map(paramKeyValue -> paramKeyValue[1]).findAny();

                    if (isoNameOptional.isEmpty()) {
                        exchange.sendResponseHeaders(400, 0);
                        return;
                    }

                    Currency currency = currencyService.getById(isoNameOptional.get());

                    try (PrintWriter writer = new PrintWriter(exchange.getResponseBody())) {
                        String responseBody = mapper.writeValueAsString(currency);
                        exchange.sendResponseHeaders(200, responseBody.length());
                        writer.write(responseBody);
                    }
                }
            }
        });

        server.createContext("/sales", (exchange) -> {
            System.out.println("GET /sales");
            try (exchange; PrintWriter writer = new PrintWriter(exchange.getResponseBody())) {
                try (InputStream inputStream = exchange.getRequestBody()) {
                    SaleRequest request = mapper.readValue(new String(inputStream.readAllBytes()), SaleRequest.class);
                    SaleResponse response = new SaleResponse();

                    if (request.id != null) {
                        response.sales = List.of(saleRepository.load(request.id));
                    } else if (request.ids != null) {
                        response.sales = saleRepository.load(request.ids);
                    } else if (request.personId != null) {
                        response.sales = saleService.loadAllByPersonId(request.personId);
                    }

                    if (response.sales != null && request.currencyId != null) {
                        Currency currency = currencyRepository.load(request.currencyId);
                        response.amount = response.sales.stream().map(sale -> sale.getAmount().get(currency)).toList();
                    }

                    if (response.sales == null || response.sales.isEmpty()) {
                        exchange.sendResponseHeaders(404, 0);
                    } else {
                        String responseString = mapper.writeValueAsString(response);
                        exchange.sendResponseHeaders(200, responseString.length());
                        writer.write(responseString);
                    }
                }
            }
        });

        server.start();
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class SaleRequest {
        private Integer id;
        private List<Integer> ids;
        private Integer currencyId;
        private Integer personId;
    }

    @Data
    @JsonInclude(Include.NON_EMPTY)
    private static class SaleResponse {
        private List<Sale> sales;
        private List<Double> amount;
    }
}