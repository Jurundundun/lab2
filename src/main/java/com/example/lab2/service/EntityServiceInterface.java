package com.example.lab2.service;

public interface EntityServiceInterface<T>{
    Iterable<T> getAll();
    T getById(Integer id);
    String save(T entity);
    String delete(T entity);
    String deleteById(Integer id);

}
