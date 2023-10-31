package com.ru.vsu.woodemai.supplier;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;
    @GetMapping
    ResponseEntity<List<SupplierDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<SupplierDto> getItemById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping
    ResponseEntity<SupplierDto> create(@RequestBody SupplierDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<SupplierDto> update(@RequestBody SupplierDto dto, @PathVariable String id) {
        return ResponseEntity.ok(service.update(dto, id));
    }
}
