package com.ru.vsu.woodemai.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ru.vsu.woodemai.category.Category;
import com.ru.vsu.woodemai.delivery.Delivery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;
    private String name;
    private String description;
    private String manufacturer;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date productionDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date expirationDate;
    private String storageCondition;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Delivery delivery;

    private double weight;
    private double price;
}

