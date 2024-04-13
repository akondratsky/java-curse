package com.github.akondratsky.factory;

import entity.Product;

public class ProductEntityFactory extends SimpleEntityFactory<Product> {
    @Override
    public Product create() {
        int id = getNextId();
        return new Product(id, "Product " + id, 10.0);
    }
}
