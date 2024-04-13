package com.github.akondratsky.repository;

import entity.Person;

import java.io.File;

public class DirectoryPersonRepository extends DirectoryRepository<Person> implements PersonRepository {
    public DirectoryPersonRepository(File dir) {
        super(dir);
    }
}
