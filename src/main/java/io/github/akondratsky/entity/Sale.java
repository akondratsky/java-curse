package io.github.akondratsky.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

@AllArgsConstructor()
@NoArgsConstructor
@Data
public class Sale implements Iterable<Product> {
    @JsonProperty
    private int id;

    /** who bought */
    @JsonProperty
    private Person person;

    @JsonProperty
    private LocalDateTime timestamp;

    /** amount of each product */
    @JsonProperty
    private Map<Product, Double> products = new TreeMap<>();

    public Sale(int id) {
        this.id = id;
        this.timestamp = LocalDateTime.now();
    }

    public Sale(int id, Person person, LocalDateTime timestamp) {
        this.id = id;
        this.person = person;
        this.timestamp = timestamp;
    }

    public Map<Currency, Double> getAmount() {
        Map<Currency, Double> amounts = new HashMap<>();

        for (Product product : products.keySet()) {
            Currency currency = product.getCurrency();
            double currentProductAmount = products.get(product) * product.getPrice();

            if (!amounts.containsKey(currency)) {
                amounts.put(currency, currentProductAmount);
            } else {
                amounts.put(currency, amounts.get(currency) + currentProductAmount);
            }
        }

        return amounts;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.keySet().iterator();
    }

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static void saveTo(File file, Sale sale) {
        try (PrintWriter writer = new PrintWriter(file)) {
            String json = getMapper().writeValueAsString(sale);
            writer.write(json);
        } catch (JsonProcessingException e) {
            System.err.println("Serializing Sale error: " + file.getName());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        }
    }

    public static Sale loadFrom(File file) {
        try (Scanner scanner = new Scanner(file)) {
            String json = scanner.nextLine();
            return getMapper().readValue(json, Sale.class);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        } catch (JsonMappingException e) {
            System.err.println("Incorrect Sale format: " + file.getName());
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing Sale: " + file.getName());
        }
        return null;
    }
}
