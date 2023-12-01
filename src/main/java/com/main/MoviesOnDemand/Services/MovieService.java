package com.main.MoviesOnDemand.Services;

import com.main.MoviesOnDemand.Dao.MovieRepository;
import com.main.MoviesOnDemand.Dao.ProducerRepository;
import com.main.MoviesOnDemand.Entity.Actor;
import com.main.MoviesOnDemand.Entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService extends GenericService<Movie, Integer>{
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> searchMovies(String searchString) {
        return movieRepository.findByNameContaining(searchString);
    }
}
