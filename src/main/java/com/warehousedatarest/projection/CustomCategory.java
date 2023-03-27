package com.warehousedatarest.projection;

import com.warehousedatarest.entity.Category;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Category.class)
public interface CustomCategory {
    Integer getId();
    String getName();
    Category getCategory();
    Boolean getActive();
}
