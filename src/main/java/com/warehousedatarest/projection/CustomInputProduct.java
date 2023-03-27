package com.warehousedatarest.projection;

import com.warehousedatarest.entity.Input;
import com.warehousedatarest.entity.InputProduct;
import com.warehousedatarest.entity.Product;
import org.h2.api.DatabaseEventListener;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(types = InputProduct.class)
public interface CustomInputProduct {
    Integer getId();
    Product getProduct();
    Integer getAmount();
    Double getPrice();
    Date getExpireDate();
    Input getInput();
}
