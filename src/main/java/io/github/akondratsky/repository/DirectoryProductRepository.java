package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Person;
import io.github.akondratsky.entity.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DirectoryProductRepository extends AbstractDirectoryRepository<Product> {
    public DirectoryProductRepository(File dir) {
        super(dir);
    }

    @Override
    public Product load(int id) {
        File file = getFileById(id);
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            String name = scanner.nextLine();
            double price = scanner.nextDouble();
            return new Product(id, name, price);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> load(List<Integer> ids) {
        return ids.stream()
                .map(this::load)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Product product) {
        File file = getFileById(product.getId());
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file))) {
            writer.println(product.getName());
            writer.println(product.getPrice());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public List<Product> loadAllByMaxPrice(double maxPrice) {
        return getAllIds().stream()
                .map(this::load)
                .filter(product -> product.getPrice() < maxPrice)
                .collect(Collectors.toList());
    }
}
