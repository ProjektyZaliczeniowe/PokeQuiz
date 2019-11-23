package com.project.pokequiz;

import java.util.List;

public interface Dao<T> {
    long save(T type);
    void update(T type);
    void delete(T type);
    T get(long id);
    List<T> getAll();
}