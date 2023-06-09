package com.warehousedatarest.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class OutputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Product product;
    private Integer amount;
    private double price;
    @ManyToOne
    private Output output;
}
