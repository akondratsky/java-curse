package repository;

import entity.Sale;

import java.io.File;
import java.util.List;

public class DirectorySaleRepository extends AbstractDirectoryRepository<Sale> {
    public DirectorySaleRepository(File dir) {
        super(dir);
    }

    @Override
    int getId(Sale sale) {
        return sale.getId();
    }
}
