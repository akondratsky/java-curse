package io.github.akondratsky.factory;

public interface EntityFactory<T> {
    T create();
}
