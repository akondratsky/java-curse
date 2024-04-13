package io.github.akondratsky.repository;

import io.github.akondratsky.entity.Identifiable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DirectoryRepository<T extends Identifiable> implements Repository<T> {
    private final File dir;

    DirectoryRepository(File dir) {
        boolean isCreated = dir.exists() || dir.mkdirs();
        if (!isCreated) {
            throw new IllegalArgumentException("Can't create directory " + dir.getAbsolutePath());
        }
        this.dir = dir;
    }

    public void save(T obj) {
        File file = getFileById(obj.getId());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(obj);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public T load(int id) {
        File file = getFileById(id);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (T) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> load(List<Integer> ids) {
        return ids.stream()
                .map(this::load)
                .collect(Collectors.toList());
    }

    private File getFileById(int id) {
        return Paths.get(dir.getAbsolutePath(), id + ".bin").toFile();
    }
}
