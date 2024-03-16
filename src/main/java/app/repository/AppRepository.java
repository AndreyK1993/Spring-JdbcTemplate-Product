package app.repository;

import java.util.List;
import java.util.Optional;

public interface AppRepository<T> {
    boolean create(T obj);
    Optional<List<T>> fetchAll();
    Optional<T> fetchById(Long id);
    boolean update(T obj);
    boolean delete(T obj);
}
