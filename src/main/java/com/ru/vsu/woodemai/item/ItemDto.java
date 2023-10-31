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
}
