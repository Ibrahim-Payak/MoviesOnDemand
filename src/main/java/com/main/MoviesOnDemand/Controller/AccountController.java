package com.main.MoviesOnDemand.Controller;

import com.main.MoviesOnDemand.Entity.User;
import com.main.MoviesOnDemand.Entity.UserType;
import com.main.MoviesOnDemand.Helper.Message;
import com.main.MoviesOnDemand.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;


    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("title", "Register Page");
        model.addAttribute("user", new User());

        System.out.println("signUpPage");
        return "signup";
    }

    @GetMapping("/login")
    public String logInPage(Model model) {
        model.addAttribute("title", "Login Page");
        return "login";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, Model model, HttpSession session) {


        var oldUser = userService.getUserByEmail(user.userEmail);

        if(oldUser == null)
        {
            user.setUserType(UserType.NORMAL);
            userService.addBook(user);
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Registration successful! kindly login with same credentials", "success"));
        }
        else{
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("This Email is already in use, please use different one","warning"));
        }

        return "signup";
    }

    @PostMapping("/validateUser")
    public String logInAndValidateUser(@ModelAttribute User user, HttpSession session) {
        User authenticatedUser = userService.getUserByEmailAndPassword(user.userEmail, user.userPassword);

        if (authenticatedUser != null) {
            session.setAttribute("user", authenticatedUser);
            session.setAttribute("authenticated", true);

            if(authenticatedUser.userType == UserType.ADMIN) session.setAttribute("userType", "admin");
            else session.setAttribute("userType", "normal");

            session.setAttribute("message", new Message("Logged In successfully, welcome to movie on demand","success"));

            return "redirect:/";
        } else {
            session.setAttribute("message", new Message("Sorry, Authentication failed..","danger"));
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("userType");
        session.setAttribute("authenticated", false);

        session.setAttribute("message", new Message("Logged Out successfully","warning"));


        return "redirect:/";
    }
}
