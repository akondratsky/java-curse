package com.github.akondratsky.entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Sale implements Iterable<Product>, Serializable, Identifiable {
    private final int id;
    private final Person person;
    private final Map<Product, Double> products = new TreeMap<>();

    /** whole price of the sale */
    private double amount = 0;

    public Sale(int id, Person person) {
        this.id = id;
        this.person = person;
    }

    public void addProduct(Product product, double quantity) {
        products.put(product, quantity);
        amount += product.getPrice();
    }

    public int getId() {
        return id;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.keySet().iterator();
    }
}
