package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Person;

public interface PersonRepository {
    void save(Person person);
    Person load(int id);
}
