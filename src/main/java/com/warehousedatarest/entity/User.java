package com.warehousedatarest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String first_name;
    private String last_name;
    @Column(nullable = false,unique = true)
    private String phone_number;
    @Column(nullable = false,unique = true)
    private String code;
    private String password;
    private boolean active;

    @ManyToMany
    private Set<Warehouse> warehouse;
}
