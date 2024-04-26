package io.github.akondratsky.repository;

public interface Repository<T> {
    void save(T person);
    T load(int id);
}
