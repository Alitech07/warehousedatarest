package com.warehousedatarest.projection;

import com.warehousedatarest.entity.Client;
import com.warehousedatarest.entity.Currency;
import com.warehousedatarest.entity.Output;
import com.warehousedatarest.entity.Warehouse;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(types = Output.class)
public interface CustomOutput {
    Integer getId();
    Date getDate();
    Warehouse getWarehouse();
    Currency getCurrency();
    String getFactoryNumber();
    String getCode();
    Client getClient();
}
