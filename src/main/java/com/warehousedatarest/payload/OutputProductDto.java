package com.warehousedatarest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputProductDto {
    private Integer productId;
    private Integer amount;
    private double price;
    private Integer outputId;
}
