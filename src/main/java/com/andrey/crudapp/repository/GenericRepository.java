package com.andrey.crudapp.repository;
import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(ID id);
    T save(T t);
    List<T> getAll();
    T update(T t);
    void deleteById(ID id);
}
