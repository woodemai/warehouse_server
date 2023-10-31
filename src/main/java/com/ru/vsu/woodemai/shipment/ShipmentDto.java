package com.ru.vsu.woodemai.shipment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDto {
    private String id;

    private String addressFrom;
    private String addressTo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;
    private String supplierId;
}
