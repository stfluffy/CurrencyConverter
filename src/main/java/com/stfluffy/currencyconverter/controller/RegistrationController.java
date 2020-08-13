package com.stfluffy.currencyconverter.controller;

import com.stfluffy.currencyconverter.dto.UserDto;
import com.stfluffy.currencyconverter.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService service;

    public RegistrationController(UserService service) {
        this.service = service;
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("error", "");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserDto getUser, Model model) {

        if (getUser.getUsername().isEmpty() && getUser.getPassword().isEmpty()) {
            model.addAttribute("regError", "er");
            return "registration";
        }

        if (!service.addUser(getUser)) {
            model.addAttribute("userError", "er");
            return "registration";
        }

        return "redirect:/login";
    }
}
