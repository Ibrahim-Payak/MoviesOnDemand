package com.main.MoviesOnDemand.Controller;


import com.main.MoviesOnDemand.Entity.Actor;
import com.main.MoviesOnDemand.Entity.Movie;
import com.main.MoviesOnDemand.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;


@Controller
public class HomeController {
    @Autowired
    private MovieService movieService;


    @GetMapping("/")
    public String home(Model model) {
        List<Movie> movies = movieService.getAll();

        model.addAttribute("title", "Home Page");
        model.addAttribute("movies", movies);

        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "searchString", required = false) String searchString, Model model) {
        List<Movie> movies = null;

        if(searchString == null) movies = movieService.getAll();
        else movies = movieService.searchMovies(searchString);


        model.addAttribute("title", "Home Page");
        model.addAttribute("movies", movies);
        model.addAttribute("searchString", searchString);

        return "home";
    }
}
