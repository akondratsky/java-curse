package io.github.akondratsky;

import io.github.akondratsky.entity.Person;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(42, "alex", 33);

        File file = new File("output.json");

        Person.saveTo(file, person);

        Person loaded = Person.loadFrom(file);

        System.out.println(loaded);
    }
}