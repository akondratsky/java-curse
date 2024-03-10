package repository;

import entity.Person;

import java.io.File;

public class DirectoryPersonRepository extends AbstractDirectoryRepository<Person> {
    public DirectoryPersonRepository(File dir) {
        super(dir);
    }

    @Override
    int getId(Person person) {
        return person.getId();
    }
}
