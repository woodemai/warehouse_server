package com.ru.vsu.woodemai.category;

import com.ru.vsu.woodemai.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String description;

    @OneToMany
    private List<Item> items;

    public Category(CategoryDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.items = new ArrayList<>();
    }
}
