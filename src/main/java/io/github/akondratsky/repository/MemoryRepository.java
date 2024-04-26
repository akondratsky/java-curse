package io.github.akondratsky.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MemoryRepository<T> implements Repository<T> {
    protected final Map<Integer, T> storage = new HashMap<>();

    abstract int getId(T obj);

    public void save(T obj) {
        storage.put(getId(obj), obj);
    }

    public T load(int id) {
        return storage.get(id);
    }

    public List<T> load(List<Integer> ids) {
        return storage.entrySet().stream()
                .filter((entry) -> ids.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .toList();
    }
}
