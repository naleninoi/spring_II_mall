package ru.gb.java1154.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.gb.java1154.dto.UserDto;
import ru.gb.java1154.service.UserService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false, defaultValue = "false") boolean isNewRegistered,
                               Model model) {
        if (Objects.equals(error, "")) {
            model.addAttribute("error", true);
        }
        if(isNewRegistered) {
            model.addAttribute("newRegistered", true);
        }
        return "login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String addNewUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        if (userService.checkUsernameOccupied(userDto.getEmail())) {
            result.addError(new ObjectError("globalError", "This e-mail address is already in use"));
        }
        if( !userDto.getPassword().equals(userDto.getPasswordMatch()) ) {
            result.addError(new FieldError("userDto", "passwordMatch", "Passwords do not match"));
        }
        if (result.hasErrors()) {
            return "register";
        }
        userService.register(userDto);
        redirectAttributes.addAttribute("isNewRegistered", true);
        return "redirect:/auth/login";
    }

}
