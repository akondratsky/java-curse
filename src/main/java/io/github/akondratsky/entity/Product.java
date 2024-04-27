package io.github.akondratsky.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product implements Comparable<Product> {
    @JsonProperty
    private int id;

    @JsonProperty
    private String name;

    @JsonProperty
    private double price;

    private Currency currency;

    @Override
    public int compareTo(Product product) {
        return this.id - product.getId();
    }

    private static ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    public static void saveTo(File file, Product product) {
        try (PrintWriter writer = new PrintWriter(file)) {
            String json = getObjectMapper().writeValueAsString(product);
            writer.write(json);
        } catch (JsonProcessingException e) {
            System.err.println("Serializing Product error: " + file.getName());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        }
    }

    public static Product loadFrom(File file) {
        try (Scanner scanner = new Scanner(file)) {
            String json = scanner.nextLine();
            return getObjectMapper().readValue(json, Product.class);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        } catch (JsonMappingException e) {
            System.err.println("Incorrect Product format: " + file.getName());
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing Product: " + file.getName());
        }
        return null;
    }
}
