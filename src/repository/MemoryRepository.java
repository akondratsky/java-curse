package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class MemoryRepository<T> implements Repository<T> {
    private final Map<Integer, T> storage = new HashMap<>();

    abstract int getId(T object);

    @Override
    public void save(T object) {
        storage.put(getId(object), object);
    }

    @Override
    public T load(int id) {
        return storage.get(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] load(List<Integer> ids) {
        return ids.stream()
                .map(storage::get)
                .filter(Objects::nonNull)
                .toArray(size -> (T[]) new Object[size]);
    }
}
