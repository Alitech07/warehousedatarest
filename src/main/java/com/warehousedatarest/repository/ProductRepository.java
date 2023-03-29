package com.warehousedatarest.repository;

import com.warehousedatarest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsProductByCode(String code);
    boolean existsProductByCodeNot(String code);
}
