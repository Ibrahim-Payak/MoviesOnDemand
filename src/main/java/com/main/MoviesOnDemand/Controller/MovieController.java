package com.main.MoviesOnDemand.Controller;

import com.main.MoviesOnDemand.Entity.*;
import com.main.MoviesOnDemand.Helper.Message;
import com.main.MoviesOnDemand.Services.ActorService;
import com.main.MoviesOnDemand.Services.CinemaService;
import com.main.MoviesOnDemand.Services.MovieService;
import com.main.MoviesOnDemand.Services.ProducerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ActorService actorService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ProducerService producerService;



    @GetMapping("/createMovie")
    public String createMovie(Model model) {
        List<Actor> actors = actorService.getAll();
        List<Cinema> cinemas = cinemaService.getAll();
        List<Producer> producers = producerService.getAll();


        model.addAttribute("movieCategories", MovieCategory.values());
        model.addAttribute("actors", actors);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("producers", producers);

        return "createMovie";
    }

    @PostMapping("/addMovie")
    public String addMovie(@ModelAttribute Movie movie, @RequestParam("file") MultipartFile imageFile, Model model, HttpSession session) {
            try{
                if (!Objects.equals(imageFile.getContentType(),"image/png") && !Objects.equals(imageFile.getContentType(),"image/jpeg") ) {
                    model.addAttribute("message", "Image should be either JPEG or PNG type");
                    return "createMovie";
                }

                movie.setImage(imageFile.getOriginalFilename());

                File saveFile = new ClassPathResource("static/image").getFile();

                Path filePath = Paths.get(saveFile.getAbsolutePath() + File.separator + imageFile.getOriginalFilename());

                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                Movie result = movieService.addBook(movie);
                model.addAttribute("movie", result);
                session.setAttribute("message", new Message("Movie added successfully", "success"));
            }catch (Exception e){
                e.printStackTrace();
            }

        return "redirect:/";
    }


    @GetMapping("/details/{id}")
    public String showMovie(@PathVariable("id") int movieId, Model model) {
        Movie movie = movieService.getBookById(movieId);

        if(movie == null) return "home";

        model.addAttribute("title", "Movie Details Page");
        model.addAttribute("movie", movie);

        return "showMovie";
    }

    @GetMapping("/editMovie/{id}")
    public String editMovie(@PathVariable("id") int movieId, Model model) {
        List<Actor> actors = actorService.getAll();
        List<Cinema> cinemas = cinemaService.getAll();
        List<Producer> producers = producerService.getAll();
        Movie movie = movieService.getBookById(movieId);


        model.addAttribute("movieCategories", MovieCategory.values());
        model.addAttribute("actors", actors);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("producers", producers);
        model.addAttribute("movie", movie);
        model.addAttribute("title", "Update movie page");

        return "updateMovie";
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable("id") int movieId, HttpSession session) {
        movieService.deleteBook(movieId);

        session.setAttribute("message", new Message("movie deleted successfully", "warning"));

        return "redirect:/";
    }

    @PostMapping("/updateMovie/{id}")
    public String updateMovie(@PathVariable("id") int movieId, @ModelAttribute Movie movie, @RequestParam("file") MultipartFile imageFile, Model model, HttpSession session) {
        try{

            //if file is not empty then
                //delete old image, set new image
            Movie oldMovie = movieService.getBookById(movieId);

            if(!imageFile.isEmpty()){
                if (!Objects.equals(imageFile.getContentType(),"image/png") && !Objects.equals(imageFile.getContentType(),"image/jpeg") ) {
                    model.addAttribute("message", "Image should be either JPEG or PNG type");
                    return "updateMovie";
                }

                //delete old image
                File saveFile = new ClassPathResource("static/image").getFile();
                File deleteFile = new File(saveFile, oldMovie.image);
                deleteFile.delete();


                //set new image
                Path filePath = Paths.get(saveFile.getAbsolutePath() + File.separator + imageFile.getOriginalFilename());
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                movie.setImage(imageFile.getOriginalFilename());
            }
            else{
                movie.setImage(oldMovie.image);
            }

            System.out.println(movie.id);
            System.out.println(movieId);

            movie.setId(movieId);

            Movie result = movieService.updateBook(movie);
            model.addAttribute("movie", result);
            session.setAttribute("message", new Message("Movie updated successfully", "success"));
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
