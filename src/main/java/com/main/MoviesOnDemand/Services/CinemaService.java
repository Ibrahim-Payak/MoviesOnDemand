package com.main.MoviesOnDemand.Services;

import com.main.MoviesOnDemand.Dao.CinemaRepository;
import com.main.MoviesOnDemand.Entity.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    public List<Cinema> getAll(){
        return (List<Cinema>) cinemaRepository.findAll();
    }

    public Cinema getBookById(int id){
        return cinemaRepository.findById(id).get();
    }

    public Cinema addBook(Cinema book){
        return cinemaRepository.save(book);
    }

    public void deleteBook(int id){
        cinemaRepository.deleteById(id);
    }

    public Cinema updateBook(Cinema book) {
        return cinemaRepository.save(book);
    }
}
