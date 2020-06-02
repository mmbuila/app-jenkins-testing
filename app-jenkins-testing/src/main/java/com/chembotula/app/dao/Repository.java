package com.chembotula.app.dao;

import java.util.List;

public interface Repository<T,K> {
    T save(T entity);
    T update(T entity);
    void delete(T entity);
    void deleteById(K id);
    T findById(K id);
    List<T> findAll();
}
