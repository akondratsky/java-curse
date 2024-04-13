package com.github.akondratsky.repository;

import entity.Sale;

import java.io.File;

public class DirectorySaleRepository extends DirectoryRepository<Sale> {
    public DirectorySaleRepository(File dir) {
        super(dir);
    }
}
