package com.agency.web.model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> extends AutoCloseable {

    Optional<T> findById(long id);
    List<T> findAll();
    void create(T entity);
    void update(T entity);
    void deleteById(long id);

    @Override
    void close();
}
