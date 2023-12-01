package com.main.MoviesOnDemand.Services;

import com.main.MoviesOnDemand.Dao.MovieRepository;
import com.main.MoviesOnDemand.Entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAll(){
        return (List<Movie>) movieRepository.findAll();
    }

    public Movie getBookById(int id){
        return movieRepository.findById(id).get();
    }

    public Movie addBook(Movie book){
        return movieRepository.save(book);
    }

    public void deleteBook(int id){
        movieRepository.deleteById(id);
    }

    public Movie updateBook(Movie book) {
        return movieRepository.save(book);
    }

    public List<Movie> searchMovies(String searchString) {
        return movieRepository.findByNameContaining(searchString);
    }
}
