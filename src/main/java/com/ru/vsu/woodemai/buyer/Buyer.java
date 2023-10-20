package com.ru.vsu.woodemai.buyer;

import com.ru.vsu.woodemai.delivery.Delivery;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String inn;

    @OneToMany
    private List<Delivery> deliveries;
}
