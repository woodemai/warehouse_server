package com.ru.vsu.woodemai.item;

import com.ru.vsu.woodemai.category.Category;
import com.ru.vsu.woodemai.category.CategoryService;
import com.ru.vsu.woodemai.supplier.Supplier;
import com.ru.vsu.woodemai.supplier.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;
    private final SupplierService supplierService;
    private final CategoryService categoryService;

    private static String getNotFoundMessage(String id) {
        return "Item with id " + id + " not found";
    }

    public List<ItemDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).toList();
    }

    public ItemDto getById(String id) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));
        return convertToDto(item);
    }

    public ItemDto create(ItemDto dto) {
        Supplier supplier = supplierService.getSupplierById(dto.getSupplierId());
        Category category = categoryService.getCategoryById(dto.getCategoryId());
        Item item = createItem(dto, supplier, category);
        return convertToDto(repository.save(item));
    }

    private Item createItem(ItemDto dto, Supplier supplier, Category category) {
        return new Item(dto, supplier, category);
    }

    private ItemDto convertToDto(Item item) {
        return new ItemDto(item);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public ItemDto updateItem(ItemDto dto, String id) {
        Supplier supplier = supplierService.getSupplierById(dto.getSupplierId());
        Category category = categoryService.getCategoryById(dto.getCategoryId());
        Item updatedItem = repository.findById(id).map(
                item -> {
                    item.setName(dto.getName());
                    item.setDescription(dto.getDescription());
                    item.setProductionDate(dto.getProductionDate());
                    item.setExpirationDate(dto.getExpirationDate());
                    item.setStorageCondition(dto.getStorageCondition());
                    item.setWeight(dto.getWeight());
                    item.setPrice(dto.getPrice());
                    item.setCategory(category);
                    item.setSupplier(supplier);
                    return repository.save(item);
                }).orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));
        return convertToDto(updatedItem);
    }
}
