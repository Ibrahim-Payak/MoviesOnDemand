package com.main.MoviesOnDemand.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public abstract class GenericService<T, ID> {

    @Autowired
    private CrudRepository<T, ID> repository;

    public List<T> getAll() {
        return (List<T>) repository.findAll();
    }

    public T getById(ID id) {
        return repository.findById(id).orElse(null);
    }

    public T addEntity(T entity) {
        return repository.save(entity);
    }

    public void deleteEntity(ID id) {
        repository.deleteById(id);
    }

    public T updateEntity(T entity) {
        return repository.save(entity);
    }
}

