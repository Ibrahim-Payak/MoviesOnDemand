package com.main.MoviesOnDemand.Services;

import com.main.MoviesOnDemand.Dao.UserRepository;
import com.main.MoviesOnDemand.Entity.Movie;
import com.main.MoviesOnDemand.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  extends GenericService<User, Integer>{
    @Autowired
    private UserRepository userRepository;


    
    public User getUserByEmailAndPassword(String userEmail, String userPassword) {
        return userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
    }
    public User getUserByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }
}
