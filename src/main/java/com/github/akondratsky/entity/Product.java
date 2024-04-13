package com.github.akondratsky.entity;

public class Product implements Comparable<Product> {
    private final int id;
    private final String name;
    private final double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Product p) {
        return Integer.compare(id, p.id);
    }

    @Override
    public String toString() {
        return name + " - " + price;
    }
}
