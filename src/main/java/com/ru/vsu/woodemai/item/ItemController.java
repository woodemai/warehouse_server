package com.ru.vsu.woodemai.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;


    @GetMapping
    ResponseEntity<List<ItemDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<ItemDto> getItemById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    ResponseEntity<ItemDto> createItem(@RequestBody ItemDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @DeleteMapping("/{id}")
    void deleteItem(@PathVariable String id) {
        service.deleteById(id);
    }

    @PatchMapping("/{id}")
    ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto dto, @PathVariable String id) {
        return ResponseEntity.ok(service.updateItem(dto, id));
    }
}
