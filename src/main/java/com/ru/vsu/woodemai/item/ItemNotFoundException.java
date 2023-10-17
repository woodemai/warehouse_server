package com.ru.vsu.woodemai.item;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String id) {
        super("Could not found an item with id: " + id);
    }
}
