package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Person;

public interface Repository<T> {
    void save(T person);
    T load(int id);
}
