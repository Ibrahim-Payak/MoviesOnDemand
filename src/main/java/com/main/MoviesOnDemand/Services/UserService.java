package com.main.MoviesOnDemand.Services;

import com.main.MoviesOnDemand.Dao.UserRepository;
import com.main.MoviesOnDemand.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return (List<User>) userRepository.findAll();
    }

    public User getBookById(int id){
        return userRepository.findById(id).get();
    }

    public User addBook(User book){
        return userRepository.save(book);
    }

    public void deleteBook(int id){
        userRepository.deleteById(id);
    }

    public User updateBook(User book) {
        return userRepository.save(book);
    }
    
    public User getUserByEmailAndPassword(String userEmail, String userPassword) {
        return userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
    }
    public User getUserByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }
}
