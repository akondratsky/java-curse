package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Identifiable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class MemoryRepository<T extends Identifiable> implements Repository<T> {
    private final Map<Integer, T> storage = new HashMap<>();

    @Override
    public void save(T object) {
        storage.put(object.getId(), object);
    }

    @Override
    public T load(int id) {
        return storage.get(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> load(List<Integer> ids) {
        return ids.stream()
                .map(storage::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
