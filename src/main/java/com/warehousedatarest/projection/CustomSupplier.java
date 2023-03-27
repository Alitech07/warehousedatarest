package com.warehousedatarest.projection;

import com.warehousedatarest.entity.Supplier;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Supplier.class)
public interface CustomSupplier {
    Integer getId();
    String getName();
    Boolean getActive();
    String getPhoneNumber();
}
