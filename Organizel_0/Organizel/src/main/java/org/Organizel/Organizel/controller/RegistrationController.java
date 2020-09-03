package org.Organizel.Organizel.controller;

import org.Organizel.Organizel.domain.Role;
import org.Organizel.Organizel.domain.User;
import org.Organizel.Organizel.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration( @ModelAttribute(name = "user")User user){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @ModelAttribute(name = "user")@Valid User user,
            BindingResult bindingResult,
            Model model){
        if(bindingResult.hasErrors()){
            return "registration";
        }
        User dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser != null){
            model.addAttribute("message","User with this username already exists");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";

    }
}
