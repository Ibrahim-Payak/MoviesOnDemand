package com.main.MoviesOnDemand.Dao;

import com.main.MoviesOnDemand.Entity.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Integer> {

}
