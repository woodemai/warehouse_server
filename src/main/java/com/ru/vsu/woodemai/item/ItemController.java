package com.ru.vsu.woodemai.item;

import com.ru.vsu.woodemai.category.Category;
import com.ru.vsu.woodemai.category.CategoryRepository;
import com.ru.vsu.woodemai.supplier.Supplier;
import com.ru.vsu.woodemai.supplier.SupplierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemRepository repository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ItemController(ItemRepository repository, CategoryRepository categoryRepository, SupplierRepository supplierRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
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
    Item updateItem(@RequestBody Item updatedItem, @PathVariable String id) {
        return repository.findById(id)
                .map(item -> {
                    item.setName(updatedItem.getName());
                    item.setDescription(updatedItem.getDescription());
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
