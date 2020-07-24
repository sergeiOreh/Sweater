package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String registrants(){
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Map<String, Object> model){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB!=null){
            model.put("message", "User already exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }

}
