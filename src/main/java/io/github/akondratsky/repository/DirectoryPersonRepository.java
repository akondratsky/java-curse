package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Person;

import java.io.File;

public class DirectoryPersonRepository implements PersonRepository {
    private final File dir;

    public DirectoryPersonRepository(File dir) {
        if (!dir.exists() && !dir.mkdir()) {
            throw new IllegalArgumentException("Error creating the directory" + dir.getAbsolutePath());
        } else if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir.getAbsolutePath() + " is not a directory");
        }
        this.dir = dir;
    }

    @Override
    public void save(Person person) {
        File file = new File(dir, person.getId() + ".json");
        Person.saveTo(file, person);
    }

    @Override
    public Person load(int id) {
        File file = new File(dir, id + ".json");
        return Person.loadFrom(file);
    }
}
