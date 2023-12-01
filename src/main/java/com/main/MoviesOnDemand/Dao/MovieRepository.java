package com.main.MoviesOnDemand.Dao;

import com.main.MoviesOnDemand.Entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    public List<Movie> findByNameContaining(String name);
}
