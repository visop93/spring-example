package com.example.springexample;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(Long id) {
        super("Item '" + id + "' is not found.");
    }
}
