package com.ru.vsu.woodemai.item;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/")
public class ItemController {
    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Item> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    Item getItemById(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @PostMapping
    Item createItem(@RequestBody Item item) {
        System.out.println(item);
        return repository.save(item);
    }

    @DeleteMapping("{id}")
    void deleteItem(@PathVariable String id) {
        repository.deleteById(id);
    }
}
