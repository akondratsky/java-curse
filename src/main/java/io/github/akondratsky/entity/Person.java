package io.github.akondratsky.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private int age;

    public static void saveTo(File file, Person person) {
        ObjectMapper mapper = new ObjectMapper();
        try (PrintWriter writer = new PrintWriter(file)) {
            String json = mapper.writeValueAsString(person);
            writer.write(json);
        } catch (JsonProcessingException e) {
            System.err.println("Serializing Person error: " + file.getName());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        }
    }

    public static Person loadFrom(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try (Scanner scanner = new Scanner(file)) {
            String json = scanner.nextLine();
            return mapper.readValue(json, Person.class);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        } catch (JsonMappingException e) {
            System.err.println("Incorrect Person format: " + file.getName());
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing Person: " + file.getName());
        }
        return null;
    }
}
