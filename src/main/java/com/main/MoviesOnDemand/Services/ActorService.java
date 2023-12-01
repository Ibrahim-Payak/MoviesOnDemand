package com.main.MoviesOnDemand.Services;

import com.main.MoviesOnDemand.Dao.ActorRepository;
import com.main.MoviesOnDemand.Entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAll(){
        return (List<Actor>) actorRepository.findAll();
    }

    public Actor getBookById(int id){
        return actorRepository.findById(id).get();
    }

    public Actor addBook(Actor book){
        return actorRepository.save(book);
    }

    public void deleteBook(int id){
        actorRepository.deleteById(id);
    }

    public Actor updateBook(Actor book) {
        return actorRepository.save(book);
    }
}
