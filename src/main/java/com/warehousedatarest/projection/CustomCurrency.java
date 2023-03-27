package com.warehousedatarest.projection;

import com.warehousedatarest.entity.Currency;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Currency.class)
public interface CustomCurrency {
    Integer getId();
    String getName();
    Boolean getActive();
}
