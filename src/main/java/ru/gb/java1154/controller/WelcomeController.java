package ru.gb.java1154.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.java1154.entity.User;
import ru.gb.java1154.service.SecurityService;

import java.util.Optional;

@Controller
public class WelcomeController {

    private final SecurityService securityService;

    public WelcomeController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("")
    public String getWelcomePage(Model model) {
        return "index";
    }

}
