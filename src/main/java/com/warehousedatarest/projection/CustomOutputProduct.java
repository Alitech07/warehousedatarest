package com.warehousedatarest.projection;

import com.warehousedatarest.entity.Output;
import com.warehousedatarest.entity.OutputProduct;
import com.warehousedatarest.entity.Product;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = OutputProduct.class)
public interface CustomOutputProduct {
    Integer getId();
    Product getProduct();
    Integer getAmount();
    Double getPrice();
    Output getOutput();
}
