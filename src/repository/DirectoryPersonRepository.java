package repository;

import entity.Person;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class DirectoryPersonRepository implements Repository<Person> {
    private final File dir;

    public DirectoryPersonRepository(File dir) {
        boolean isCreated = dir.exists() || dir.mkdirs();
        if (!isCreated) {
            throw new IllegalArgumentException("Can't create directory " + dir.getAbsolutePath());
        }
        this.dir = dir;
    }

    public void save(Person person) {
        Person.saveTo(getFileById(person.getId()), person);
    }

    public Person load(int id) {
        return Person.loadFrom(getFileById(id));
    }

    public Person[] load(List<Integer> ids) {
        return new Person[0];
    }

    private File getFileById(int id) {
        return Paths.get(dir.getAbsolutePath(), id + ".bin").toFile();
    }
}
