package com.ru.vsu.woodemai.supplier;

import com.ru.vsu.woodemai.item.ItemNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierRepository repository;

    public SupplierController(SupplierRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Supplier> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Supplier getItemById(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }
    @PostMapping
    Supplier create(@RequestBody Supplier supplier) {
        return repository.save(supplier);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    Supplier update(@RequestBody Supplier updatedSupplier, @PathVariable String id) {
        return repository.findById(id)
                .map(supplier -> {
                    supplier.setName(updatedSupplier.getName());
                    supplier.setInn(updatedSupplier.getInn());
                    return repository.save(supplier);
                }).orElseGet(() -> {
                    updatedSupplier.setId(id);
                    return repository.save(updatedSupplier);
                });
    }
}
