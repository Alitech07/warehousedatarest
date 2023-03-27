package com.warehousedatarest.projection;

import com.warehousedatarest.entity.Client;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Client.class)
public interface CustomClient {
    Integer getId();
    String getName();
    String getPhotoNumber();
}
