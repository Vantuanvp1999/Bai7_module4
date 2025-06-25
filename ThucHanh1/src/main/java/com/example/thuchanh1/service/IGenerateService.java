package com.example.thuchanh1.service;

import com.example.thuchanh1.model.Customer;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();
    void save(T t);
    Optional<T> findById(Long id);
    void deleteById(Long id);

}
