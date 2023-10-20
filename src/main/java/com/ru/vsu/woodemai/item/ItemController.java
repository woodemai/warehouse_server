package com.ru.vsu.woodemai.item;

import com.ru.vsu.woodemai.category.Category;
import com.ru.vsu.woodemai.category.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemRepository repository;
    private final CategoryRepository categoryRepository;

    public ItemController(ItemRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
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
    @PostMapping
    Item createItem(@RequestBody ItemCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategory()).orElseThrow(() -> new ItemNotFoundException(request.getCategory()));
        Item item = new Item(request.getId(),
                request.getName(),
                request.getDescription(),
                request.getManufacturer(),
                request.getProductionDate(),
                request.getExpirationDate(),
                request.getStorageCondition(),
                category,
                null,
                request.getWeight(),
                request.getPrice()
        );
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
