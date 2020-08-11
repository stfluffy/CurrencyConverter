package com.stfluffy.currencyconverter.controller;

import com.stfluffy.currencyconverter.model.User;
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
    public String addUser(User getUser, Model model) {
        if (!service.addUser(getUser)) {
            model.addAttribute("error", "Пользователь существует!");
            return "registration";
        }

        return "redirect:/login";
    }
}
