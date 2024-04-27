package io.github.akondratsky.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private double price;

    @Override
    public int compareTo(Product product) {
        return this.id - product.getId();
    }
}
