package com.warehousedatarest.projection;

import com.warehousedatarest.entity.Measurement;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Measurement.class)
public interface CustomMeasurement {
    Integer getId();
    String getName();
    Boolean getActive();
}
