package com.aljo.smedia;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/") /* Root directory same as ("/") */
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/register")
    public String pRegister(User user) {
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        String encodedPW = pwEncoder.encode(user.getPassword());
        user.setPassword(encodedPW);
        userRepo.save(user);
        return "register_success";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
}

