package com.main.MoviesOnDemand.Dao;

import com.main.MoviesOnDemand.Entity.Cinema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Integer> {
}
