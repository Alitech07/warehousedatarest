package com.warehousedatarest.repository;


import com.warehousedatarest.entity.User;
import com.warehousedatarest.projection.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user",excerptProjection = CustomUser.class)
public interface UserRepository extends JpaRepository<User,Integer> {
}
