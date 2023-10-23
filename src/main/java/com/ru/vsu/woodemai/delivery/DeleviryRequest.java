package com.ru.vsu.woodemai.delivery;

import com.ru.vsu.woodemai.buyer.Buyer;
import com.ru.vsu.woodemai.item.Item;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class DeleviryRequest {
    private String id;
    private String addressFrom;
    private String addressTo;
    private Date dateFrom;
    private Date dateTo;
    private String buyer;
    private List<String> items;
}
