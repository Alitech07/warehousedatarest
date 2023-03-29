package com.warehousedatarest.repository;

import com.warehousedatarest.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputRepository extends JpaRepository<Input,Integer> {
    boolean existsInputByCode(String code);
    boolean existsInputByCodeNot(String code);
}
