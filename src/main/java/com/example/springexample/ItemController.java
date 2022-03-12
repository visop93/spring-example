package com.example.springexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ItemController implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    //REST items showcase
    @GetMapping("/items")
    @ResponseBody
    List<Item> all() {
        return repository.findAll();
    }

    @GetMapping("/items/{id}")
    @ResponseBody
    Item one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    //add items operations
    @GetMapping("/items/add")
    public String showAddForm (Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "itemAddForm";
    }

    @PostMapping("/items/add")
    public String addItem(@Valid  Item item, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "itemAddForm";
        }
        repository.save(item);
        model.addAttribute("id", item.getId());
        return "itemAddPositiveResult";
    }

}
