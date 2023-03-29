package com.warehousedatarest.repository;

import com.warehousedatarest.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
}
