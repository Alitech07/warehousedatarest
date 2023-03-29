package com.warehousedatarest.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDto {
    private Date date;
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String facturNumber;
    @NotNull(message = "shtrix kod kiritilmadi.")
    private String code;
}
