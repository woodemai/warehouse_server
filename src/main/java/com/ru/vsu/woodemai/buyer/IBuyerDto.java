package com.ru.vsu.woodemai.buyer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IBuyerDto {

    private String id;

    private String name;
    private String inn;
}
