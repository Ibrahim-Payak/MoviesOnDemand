package com.main.MoviesOnDemand.Controller;

import com.main.MoviesOnDemand.Entity.*;
import com.main.MoviesOnDemand.Services.CinemaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Controller
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;



    @GetMapping("/createCinema")
    public String createCinema(Model model) {
        model.addAttribute("title", "Create Cinema");
        return "createCinema";
    }

    @PostMapping("/addCinema")
    public String addCinema(@ModelAttribute Cinema cinema, @RequestParam("file") MultipartFile imageFile, Model model) {
        try{
            if (!Objects.equals(imageFile.getContentType(),"image/png") && !Objects.equals(imageFile.getContentType(),"image/jpeg") ) {
                model.addAttribute("message", "Image should be either JPEG or PNG type");
                return "createMovie";
            }

            cinema.setCinemaLogo(imageFile.getOriginalFilename());

            File saveFile = new ClassPathResource("static/image").getFile();

            Path filePath = Paths.get(saveFile.getAbsolutePath() + File.separator + imageFile.getOriginalFilename());

            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Cinema result = cinemaService.addEntity(cinema);
            model.addAttribute("cinema", result);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
