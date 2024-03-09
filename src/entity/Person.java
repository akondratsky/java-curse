package entity;

import java.io.*;

public class Person implements Serializable {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void saveTo(File file, Person person) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(person);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static Person loadFrom(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Person) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return name + ", who is " + age + " years old.";
    }
}
