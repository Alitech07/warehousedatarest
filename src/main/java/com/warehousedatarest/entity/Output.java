package com.warehousedatarest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Currency  currency;
    @Column(unique = true,nullable = false)
    private String fucture_number;
    @Column(unique = true,nullable = false)
    private String code;
    @ManyToOne
    private Client client;
}
