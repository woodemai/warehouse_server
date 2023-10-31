package com.ru.vsu.woodemai.delivery;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

    private String id;

    private String addressFrom;
    private String addressTo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;
    private String status;
    private int progress;

    private String buyerId;
}
