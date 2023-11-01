package com.ru.vsu.woodemai.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private String id;

    private String name;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;
    private String storageCondition;

    private String supplierId;

    private String categoryId;

    private String deliveryId;

    private double weight;
    private double price;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.productionDate = item.getProductionDate();
        this.expirationDate = item.getExpirationDate();
        this.storageCondition = item.getStorageCondition();
        this.weight = item.getWeight();
        this.price = item.getPrice();
        this.supplierId = item.getSupplier().getId();
        this.categoryId = item.getCategory().getId();
    }
}
