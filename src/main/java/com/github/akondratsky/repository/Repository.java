package com.github.akondratsky.repository;

import java.util.List;

public interface Repository<T> {
    void save(T object);

    T load(int id);

    T[] load(List<Integer> ids);
}
