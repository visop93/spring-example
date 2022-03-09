package com.example.springexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/items")
    List<Item> all() {
        return repository.findAll();
    }

    @GetMapping("/items/{id}")
    Item one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

}
