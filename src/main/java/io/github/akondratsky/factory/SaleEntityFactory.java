package io.github.akondratsky.factory;

import io.github.akondratsky.entity.Sale;

public class SaleEntityFactory extends SimpleEntityFactory<Sale> {

    @Override
    public Sale create() {
        return new Sale(getNextId());
    }
}
