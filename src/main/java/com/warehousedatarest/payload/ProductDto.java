package com.warehousedatarest.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull(message = "Mahsulot nomi kiritilmadi.")
    private String name;
    private Integer categoryId;
    private Integer photoId;
    @NotNull(message = "shtrix kod kiritlmadi.")
    private String code;
    private Integer measurmentId;
}
