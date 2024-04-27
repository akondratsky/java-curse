package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Product;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DirectoryProductRepository implements Repository<Product> {
    private final File dir;

    public DirectoryProductRepository(File dir) {
        if (!dir.exists() && !dir.mkdir()) {
            throw new IllegalArgumentException("Error creating the directory" + dir.getAbsolutePath());
        } else if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir.getAbsolutePath() + " is not a directory");
        }
        this.dir = dir;
    }

    @Override
    public void save(Product product) {
        File file = new File(dir, product.getId() + ".json");
        Product.saveTo(file, product);
    }

    @Override
    public Product load(int id) {
        return Product.loadFrom(new File(dir, id + ".json"));
    }

    @Override
    public List<Product> load(List<Integer> ids) {
        return ids.stream().map(this::load).toList();
    }

    @Override
    public List<Product> loadAll() {
        File[] files = dir.listFiles();
        if (files == null) {
            return List.of();
        }
        return Arrays.stream(files)
                .map(Product::loadFrom)
                .toList();
    }
}
