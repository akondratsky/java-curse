package io.github.akondratsky;

import io.github.akondratsky.entity.Person;
import io.github.akondratsky.entity.Sale;
import io.github.akondratsky.repository.DirectoryPersonRepository;

import java.io.File;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        Sale sale = new Sale(42);
        System.out.println(sale);
    }
}