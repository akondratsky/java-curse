package io.github.akondratsky.service;

import io.github.akondratsky.entity.Product;
import io.github.akondratsky.repository.MemoryRepositoryByLambda;
import io.github.akondratsky.repository.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductServiceTests {
    @Test
    public void loadAllByMaxPrice() {
        Repository<Product> repository = new MemoryRepositoryByLambda<>(Product::getId);
        repository.save(new Product(1, "A", 1.00, null));
        repository.save(new Product(2, "B", 4.20, null));
        ProductService productService = new ProductService(repository);

        List<Product> products = productService.loadAllByMaxPrice(3.50);

        Assertions.assertEquals(1, products.size());
    }
}
