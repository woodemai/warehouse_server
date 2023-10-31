package com.ru.vsu.woodemai.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService service;

    @GetMapping
    ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    ResponseEntity<CategoryDto> create(@RequestBody CategoryDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<CategoryDto> update(@RequestBody CategoryDto dto, @PathVariable String id) {
        return ResponseEntity.ok(service.update(dto, id));
    }
}
