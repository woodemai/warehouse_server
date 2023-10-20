package com.ru.vsu.woodemai.delivery;

import com.ru.vsu.woodemai.buyer.Buyer;
import com.ru.vsu.woodemai.item.Item;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String addressFrom;
    private String addressTo;
    private Date dateFrom;
    private Date dateTo;

    @OneToOne
    private Buyer buyer;
    @OneToMany
    private List<Item> items;
}
