package io.github.akondratsky.entity;

import java.io.Serializable;

public class Person implements Serializable, Identifiable {
    private final int id;
    private final String name;
    private final int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + ", who is " + age + " years old.";
    }
}
