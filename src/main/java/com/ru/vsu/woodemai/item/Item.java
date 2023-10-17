package com.ru.vsu.woodemai.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;
    private String name;
    private String description;
    private String manufacturer;

    @JsonFormat(pattern="DD-MM-YYYY")
    private Date productionDate;
    @JsonFormat(pattern="DD-MM-YYYY")
    private Date expirationDate;
    private String storageCondition;

    private double weight;
    private double price;
}

