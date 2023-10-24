package com.ru.vsu.woodemai.item;

import com.ru.vsu.woodemai.category.Category;
import com.ru.vsu.woodemai.category.CategoryRepository;
import com.ru.vsu.woodemai.supplier.Supplier;
import com.ru.vsu.woodemai.supplier.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository repository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;


    @GetMapping
    ResponseEntity<List<Item>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Item> getItemById(@PathVariable String id) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        return ResponseEntity.ok(item);
    }

    @PostMapping
    Item createItem(@RequestBody ItemRequest request) {
        Category category = categoryRepository.findById(request.getCategory()).orElseThrow(() -> new ItemNotFoundException(request.getCategory()));
        Supplier supplier = supplierRepository.findById(request.getSupplier()).orElseThrow(() -> new ItemNotFoundException(request.getSupplier()));
        Item item = new Item(request.getId(),
                request.getName(),
                request.getDescription(),
                request.getProductionDate(),
                request.getExpirationDate(),
                request.getStorageCondition(),
                supplier,
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
    Item updateItem(@RequestBody ItemRequest request, @PathVariable String id) {
        Supplier supplier = supplierRepository.findById(request.getSupplier()).orElseThrow();
        Category category = categoryRepository.findById(request.getCategory()).orElseThrow();
        return repository.findById(id)
                .map(item -> {
                    item.setName(request.getName());
                    item.setDescription(request.getDescription());
                    item.setProductionDate(request.getProductionDate());
                    item.setExpirationDate(request.getExpirationDate());
                    item.setStorageCondition(request.getStorageCondition());
                    item.setWeight(request.getWeight());
                    item.setPrice(request.getPrice());
                    item.setCategory(category);
                    item.setSupplier(supplier);
                    return repository.save(item);
                }).orElseGet(() -> {
                    request.setId(id);
                    return repository.save(new Item(id,
                            request.getName(),
                            request.getDescription(),
                            request.getProductionDate(),
                            request.getExpirationDate(),
                            request.getStorageCondition(),
                            supplier,
                            category,
                            null,
                            request.getWeight(),
                            request.getPrice()));
                });
    }
}
