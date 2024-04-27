package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Sale;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DirectorySaleRepository implements Repository<Sale> {
    private final File dir;

    public DirectorySaleRepository(File dir) {
        if (!dir.exists() && !dir.mkdir()) {
            throw new IllegalArgumentException("Error creating the directory" + dir.getAbsolutePath());
        } else if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir.getAbsolutePath() + " is not a directory");
        }
        this.dir = dir;
    }

    public List<Sale> loadAllByPersonId(int id) {
        return loadAll().stream()
            .filter(sale -> sale != null && sale.getId() == id)
            .toList();
    }

    @Override
    public void save(Sale sale) {
        File file = new File(dir, sale.getId() + ".json");
        Sale.saveTo(file, sale);
    }

    @Override
    public Sale load(int id) {
        File file = new File(dir, id + ".json");
        return Sale.loadFrom(file);
    }

    @Override
    public List<Sale> load(List<Integer> ids) {
        return ids.stream().map(this::load).toList();
    }

    @Override
    public List<Sale> loadAll() {
        File[] files = dir.listFiles();

        if (files == null) {
            return List.of();
        }

        return Arrays.stream(files)
                .map(Sale::loadFrom)
                .toList();
    }
}
