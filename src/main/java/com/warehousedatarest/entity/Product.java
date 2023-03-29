package com.warehousedatarest.entity;

import com.warehousedatarest.entity.template.AbsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {
    @ManyToOne
    private Category category;
    @OneToOne
    private Attachment attachment;
    @Column(nullable = false,unique = true)
    private String code;
    @ManyToOne
    private Measurement measurement;
}
