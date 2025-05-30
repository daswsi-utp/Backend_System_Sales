package com.microservice.inventory.persistence;


import com.microservice.inventory.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository <Brand, Long>{

}
