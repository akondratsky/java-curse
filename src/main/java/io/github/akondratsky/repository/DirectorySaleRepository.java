package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Sale;

import java.io.File;
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
}
