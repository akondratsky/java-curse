package io.github.akondratsky.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class MemoryRepository<T> implements Repository<T> {
    private final Map<Integer, T> storage = new HashMap<>();

    protected abstract int getId(T object);

    @Override
    public void save(T object) {
        storage.put(getId(object), object);
    }

    @Override
    public T load(int id) {
        return storage.get(id);
    }

    @Override
    public List<T> load(List<Integer> ids) {
        return ids.stream()
                .map(storage::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
