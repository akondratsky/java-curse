package io.github.akondratsky;

import io.github.akondratsky.entity.Person;
import io.github.akondratsky.repository.DirectoryRepository;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(42, "alex", 33);

        File directory = new File("output_persons");

        DirectoryRepository personRepo = new DirectoryRepository(directory);

        personRepo.save(person);

        Person loaded = personRepo.load(42);

        System.out.println(loaded);
    }
}