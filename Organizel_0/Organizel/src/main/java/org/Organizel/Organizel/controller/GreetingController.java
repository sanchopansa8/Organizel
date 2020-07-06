package org.Organizel.Organizel.controller;
import org.Organizel.Organizel.domain.Tasks;
import org.Organizel.Organizel.domain.User;
import org.Organizel.Organizel.repos.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Date;
import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}