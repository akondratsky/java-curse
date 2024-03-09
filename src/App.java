import entity.Person;

import java.io.File;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Welcome to the Person Management System");
        System.out.println("1. Input a person");
        System.out.println("2. Load a person from file");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                inputPerson();
                break;
            case 2:
                loadPerson();
            default:
                System.out.println("Invalid choice");
        }
    }

    private void loadPerson() {
        System.out.println("Enter the file name:");
        String fileName = scanner.next();
        scanner.nextLine();
        Person person = Person.loadFrom(new File(fileName));

        if (person != null) {
            System.out.println("Person loaded: " + person);
        }
    }

    private void inputPerson() {
        System.out.println("Enter the name:");
        String name = scanner.next();
        scanner.nextLine();
        System.out.println("Enter the age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        Person person = new Person(42, name, age);
        System.out.println("Input file name to save:");
        String fileName = scanner.next();
        scanner.nextLine();
        File file = new File(fileName);
        Person.saveTo(file, person);
        System.out.println("Person created: " + person);
    }
}
