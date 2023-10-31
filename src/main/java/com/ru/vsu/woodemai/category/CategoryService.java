package com.ru.vsu.woodemai.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    private static String getNotFoundMessage(String id) {
        return "Category with id " + id + " not found";
    }

    public Category getCategoryById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));
    }

    public CategoryDto getById(String id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));
        return convertToDto(category);
    }

    public List<CategoryDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).toList();
    }

    private CategoryDto convertToDto(Category category) {
        return new CategoryDto(category);
    }

    public CategoryDto create(CategoryDto dto) {
        return convertToDto(repository.save(new Category(dto)));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public CategoryDto update(CategoryDto dto, String id) {
        Category updatedCategory = repository.findById(id)
                .map(category -> {
                    category.setName(dto.getName());
                    category.setDescription(dto.getDescription());
                    return category;
                }).orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));
        return convertToDto(updatedCategory);
    }
}
