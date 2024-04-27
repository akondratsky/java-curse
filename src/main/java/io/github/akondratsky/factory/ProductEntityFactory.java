package io.github.akondratsky.factory;

import io.github.akondratsky.entity.Product;

public class ProductEntityFactory extends SimpleEntityFactory<Product> {

    @Override
    public Product create() {
        return new Product(getNextId(), "", 0.0);
    }
}
