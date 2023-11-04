package com.ru.vsu.woodemai.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ru.vsu.woodemai.category.Category;
import com.ru.vsu.woodemai.supplier.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productionDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expirationDate;
    private String storageCondition;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Category category;


    private double weight;
    private double price;

    public Item(ItemDto dto, Supplier supplier, Category category) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.productionDate = dto.getProductionDate();
        this.expirationDate = dto.getExpirationDate();
        this.storageCondition = dto.getStorageCondition();
        this.weight = dto.getWeight();
        this.price = dto.getPrice();
        this.supplier = supplier;
        this.category = category;
    }
}

