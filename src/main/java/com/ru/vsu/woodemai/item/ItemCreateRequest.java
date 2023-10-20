package com.ru.vsu.woodemai.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ItemCreateRequest {
    private String id;
    private String name;
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date productionDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date expirationDate;
    private String storageCondition;

    private String category;
    private String supplier;

    private double weight;
    private double price;
}
