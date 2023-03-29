package com.warehousedatarest.repository;

import com.warehousedatarest.entity.Warehouse;
import com.warehousedatarest.projection.CustomWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
}
