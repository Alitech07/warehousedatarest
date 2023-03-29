package com.warehousedatarest.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDto {
    private Date date;
    private Integer warehouseId;
    private Integer currencyId;
    private String facturNumber;
    @NotNull(message = "Shtrix kod kiritilmadi.")
    private String code;
    private Integer clientId;
}
