package com.warehousedatarest.projection;

import com.warehousedatarest.entity.Category;
import com.warehousedatarest.entity.Measurement;
import com.warehousedatarest.entity.Product;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Product.class)
public interface CustomProduct {
    Integer getId();
    String getName();
    Category getCategory();
    String getCode();
    Measurement getMeasurement();
}
