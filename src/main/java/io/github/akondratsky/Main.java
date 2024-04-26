package io.github.akondratsky;

import io.github.akondratsky.entity.Person;
import io.github.akondratsky.repository.DirectoryPersonRepository;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(42, "alex", 33);

        File directory = new File("output_persons");

        DirectoryPersonRepository personRepo = new DirectoryPersonRepository(directory);

        personRepo.save(person);

        Person loaded = personRepo.load(42);

        System.out.println(loaded);
    }
}