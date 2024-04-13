package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Person;

import java.io.File;

public class DirectoryPersonRepository extends DirectoryRepository<Person> implements PersonRepository {
    public DirectoryPersonRepository(File dir) {
        super(dir);
    }
}
