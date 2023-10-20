package com.ru.vsu.woodemai.shipment;

import com.ru.vsu.woodemai.item.Item;
import com.ru.vsu.woodemai.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String addressFrom;
    private String addressTo;
    private Date dateFrom;
    private Date dateTo;

    @OneToOne
    private Supplier supplier;
    @OneToMany
    private List<Item> items;
}
