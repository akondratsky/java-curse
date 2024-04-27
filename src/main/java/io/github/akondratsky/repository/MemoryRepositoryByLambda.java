package io.github.akondratsky.repository;

import java.util.function.Function;

public class MemoryRepositoryByLambda<T> extends MemoryRepository<T> {
    private final Function<T, Integer> getIdLambda;

    public MemoryRepositoryByLambda(Function<T, Integer> getIdLambda) {
        this.getIdLambda = getIdLambda;
    }

    @Override
    int getId(T obj) {
        return getIdLambda.apply(obj);
    }
}
