package com.ru.vsu.woodemai.supplier;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository repository;

    private static String getNotFoundMessage(String id) {
        return "Supplier with id " + id + " not found";
    }

    public Supplier getSupplierById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));
    }

    public List<SupplierDto> getAll() {
        return repository.findAll().stream().map(this::convertToDto).toList();
    }

    private SupplierDto convertToDto(Supplier supplier) {
        return new SupplierDto(supplier);
    }

    public SupplierDto getById(String id) {
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));
        return convertToDto(supplier);
    }

    public SupplierDto create(SupplierDto dto) {
        return convertToDto(repository.save(new Supplier(dto)));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public SupplierDto update(SupplierDto dto, String id) {
        Supplier updatedSupplier = repository.findById(id)
                .map(supplier -> {
                    supplier.setName(dto.getName());
                    supplier.setInn(dto.getInn());
                    return supplier;
                })
                .orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));
        return convertToDto(updatedSupplier);
    }

}
