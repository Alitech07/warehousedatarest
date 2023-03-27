package com.warehousedatarest.projection;

import com.warehousedatarest.entity.Currency;
import com.warehousedatarest.entity.Input;
import com.warehousedatarest.entity.Supplier;
import com.warehousedatarest.entity.Warehouse;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(types = Input.class)
public interface CustomInput {
    Integer getId();
    Date getDate();
    Warehouse getWarehouse();
    Supplier getSupplier();
    Currency getCurrency();
    String getFactureNumber();
    String getCode();
}
