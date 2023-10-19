package com.ru.vsu.woodemai.item;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Item> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Item getItemById(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }
    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping
    Item createItem(@RequestBody Item item) {
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    void deleteItem(@PathVariable String id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    Item updateItem(@RequestBody Item updatedItem, @PathVariable String id) {
        return repository.findById(id)
                .map(item -> {
                    item.setName(updatedItem.getName());
                    item.setDescription(updatedItem.getDescription());
                    item.setManufacturer(updatedItem.getManufacturer());
                    item.setProductionDate(updatedItem.getProductionDate());
                    item.setExpirationDate(updatedItem.getExpirationDate());
                    item.setStorageCondition(updatedItem.getStorageCondition());
                    item.setWeight(updatedItem.getWeight());
                    item.setPrice(updatedItem.getPrice());
                    return repository.save(item);
                }).orElseGet(() -> {
                    updatedItem.setId(id);
                    return repository.save(updatedItem);
                });
    }
}
