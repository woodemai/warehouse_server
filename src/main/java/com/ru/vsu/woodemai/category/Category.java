package com.ru.vsu.woodemai.category;

import com.ru.vsu.woodemai.item.Item;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String description;

    @OneToMany
    private List<Item> items;
}
