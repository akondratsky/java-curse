package io.github.akondratsky.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

@AllArgsConstructor
public class JsonFileRepository<T> implements Repository<T> {
    private ObjectMapper mapper;
    private TypeReference<T> type;
    private File dir;
    private Function<T, Integer> getIdLambda;

    @Override
    public void save(T obj) {
        File file = new File(dir, getIdLambda.apply(obj) + ".json");
        try (PrintWriter writer = new PrintWriter(file)) {
            String json = mapper.writeValueAsString(obj);
            writer.write(json);
        } catch (JsonProcessingException e) {
            System.err.println("Serializing Sale error: " + file.getName());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        }
    }

    @Override
    public T load(int id) {
        return loadFromFile(new File(dir, id + ".json"));
    }

    @Override
    public List<T> load(List<Integer> ids) {
        return ids.stream().map(this::load).toList();
    }

    @Override
    public List<T> loadAll() {
        File[] files = dir.listFiles();
        if (files == null) {
            return List.of();
        }
        return Arrays.stream(files)
                .map(this::loadFromFile)
                .toList();
    }

    private T loadFromFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            String json = scanner.nextLine();
            return mapper.readValue(json, type);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        } catch (JsonMappingException e) {
            System.err.println("Incorrect object format: " + file.getName());
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing object: " + file.getName());
        }
        return null;
    }
}
