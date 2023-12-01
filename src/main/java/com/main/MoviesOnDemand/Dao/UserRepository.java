package com.main.MoviesOnDemand.Dao;

import com.main.MoviesOnDemand.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserEmailAndUserPassword(String userEmail, String userPassword);
    User findByUserEmail(String userEmail);
}

