package com.microservice.sales.microservice_sales.persistence;

import com.microservice.sales.microservice_sales.entities.Registry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistryRepository extends JpaRepository<Registry, Long> {
}
