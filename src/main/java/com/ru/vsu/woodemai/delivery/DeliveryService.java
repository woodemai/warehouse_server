package com.ru.vsu.woodemai.delivery;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository repository;

    public Delivery getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Delivery with id " + id + " not found"));
    }
}
