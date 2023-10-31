package com.ru.vsu.woodemai.delivery;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;
    private String status;
    private int progress;

    @OneToOne
    private Buyer buyer;
    @OneToMany
    private List<Item> items;
}
