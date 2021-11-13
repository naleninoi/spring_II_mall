package ru.gb.java1154.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.java1154.render.UserR;
import ru.gb.java1154.entity.User;
import ru.gb.java1154.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAllUsers();
        List<UserR> result = users.stream().map(UserR::new).collect(Collectors.toList());
        model.addAttribute("users", result);
        return "users";
    }
}
