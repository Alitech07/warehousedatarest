package com.warehousedatarest.repository;

import com.warehousedatarest.entity.Client;
import com.warehousedatarest.projection.CustomClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "client",excerptProjection = CustomClient.class)
public interface ClientRepository extends JpaRepository<Client,Integer> {
}
