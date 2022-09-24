package com.sg.vttpminiproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sg.vttpminiproject.models.User;
import com.sg.vttpminiproject.services.UserService;

@Controller
@RequestMapping("/registeruser")
public class UserController {

    @Autowired
    private UserService userSvc;

    @GetMapping
    public String registerUser(Model model) {

        List<User> users = userSvc.registerUser();
        model.addAttribute("users", users);
        return "registeruser";

    }
}
