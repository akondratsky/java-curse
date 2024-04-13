package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DirectoryPersonRepository extends AbstractDirectoryRepository<Person> implements PersonRepository {
    public DirectoryPersonRepository(File directory) {
        super(directory);
    }

    public Person load(int id) {
        File file = getFileById(id);
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            String name = scanner.nextLine();
            int age = scanner.nextInt();
            return new Person(id, name, age);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    public List<Person> load(List<Integer> ids) {
        return ids.stream()
                .map(this::load)
                .collect(Collectors.toList());
    }

    public void save(Person person) {
        File file = getFileById(person.getId());
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file))) {
            writer.println(person.getName());
            writer.println(person.getAge());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
