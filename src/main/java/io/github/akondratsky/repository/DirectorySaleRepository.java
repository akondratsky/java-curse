package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Product;
import io.github.akondratsky.entity.Sale;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class DirectorySaleRepository extends AbstractDirectoryRepository<Sale> {
    public DirectorySaleRepository(File dir) {
        super(dir);
    }

    @Override
    public Sale load(int id) {
        // TODO: implement load sale method
        return null;
    }

    @Override
    public List<Sale> load(List<Integer> ids) {
        // TODO: implement load sale method
        return null;
    }

    @Override
    public void save(Sale sale) {
       // TODO: implement save sale method
    }

    public List<Sale> loadAllByPersonId(int personId) {
        // TODO: В классе DirectorySaleRepository создать метод loadAllByPersonId ,
        // принимающий аргумент id типа int , и возвращает значение типа
        // List<Sale> , которое представляет собой все покупки совершенные
        // человеком с определенным id . (Использовать StreamAPI )
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
