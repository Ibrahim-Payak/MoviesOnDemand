package com.main.MoviesOnDemand.Dao;

import com.main.MoviesOnDemand.Entity.Producer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends CrudRepository<Producer, Integer> {
}
