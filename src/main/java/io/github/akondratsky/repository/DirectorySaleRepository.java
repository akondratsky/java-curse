package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Sale;

import java.io.File;

public class DirectorySaleRepository extends DirectoryRepository<Sale> {
    public DirectorySaleRepository(File dir) {
        super(dir);
    }
}
