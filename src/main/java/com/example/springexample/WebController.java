package com.example.springexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    //add dependency
    @Autowired
    private final ItemRepository repository;

    public WebController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String loadInfo(Model model) {
        model.addAttribute("amount", repository.count());
        return "index";
    }
}
