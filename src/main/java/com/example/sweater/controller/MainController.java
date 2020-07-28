package com.example.sweater.controller;


import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String tag, Model model) {
        Iterable<Message> messages;

        if (tag != null && !tag.isEmpty()) {
            messages = messageRepository.findAllByTag(tag);
        } else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/add")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }
}
