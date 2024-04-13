package io.github.akondratsky.repository;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractDirectoryRepository<T> implements Repository<T> {
    private final File directory;

    AbstractDirectoryRepository(File directory) {
        boolean isCreated = directory.exists() || directory.mkdirs();
        if (!isCreated) {
            throw new IllegalArgumentException("Can't create directory " + directory.getAbsolutePath());
        }
        this.directory = directory;
    }

    public abstract T load(int id);

    public abstract List<T> load(List<Integer> ids);

    public abstract void save(T object);

    protected File getFileById(int id) {
        return Paths.get(directory.getAbsolutePath(), id + ".bin").toFile();
    }

    protected List<Integer> getAllIds() {
        String[] files = directory.list();
        if (files == null) {
            return List.of();
        }
        return Stream.of(files)
                .map(fileName -> fileName.substring(0, fileName.length() - 4))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
