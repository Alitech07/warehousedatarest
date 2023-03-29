package com.warehousedatarest.repository;

import com.warehousedatarest.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputRepository extends JpaRepository<Output,Integer> {
    boolean existsOutputByCode(String code);
    boolean existsOutputByCodeNot(String code);
}
