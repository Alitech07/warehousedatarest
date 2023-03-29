package com.warehousedatarest.entity.template;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean active;
}
