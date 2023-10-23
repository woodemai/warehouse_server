package com.ru.vsu.woodemai.category;

import com.ru.vsu.woodemai.item.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository repository;

    @GetMapping
    List<Category> getAll(){return repository.findAll();}

    @GetMapping("/{id}")
    Category getById(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }
    @PostMapping
    Category create(@RequestBody Category category) {
        return repository.save(category);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    Category update(@RequestBody Category updatedCategory, @PathVariable String id) {
        return repository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    category.setDescription(updatedCategory.getName());
                    return repository.save(category);
                }).orElseGet(() -> {
                    updatedCategory.setId(id);
                    return repository.save(updatedCategory);
                });
    }
}
