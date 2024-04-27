package io.github.akondratsky.entity;


import lombok.NonNull;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Sale implements Iterable<Product> {
    private int id;
    /** price of all sale */
    private double amount;
    /** who bought */
    private Person person;
    /** amount of each product */
    private Map<Product, Double> products = new TreeMap<>();


    @Override
    public Iterator<Product> iterator() {
        return products.keySet().iterator();
    }
}
