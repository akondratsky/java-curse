package io.github.akondratsky.repository;

import java.util.List;

public interface Repository<T> {
    void save(T obj);
    T load(int id);
    List<T> load(List<Integer> ids);
    List<T> loadAll();
}
