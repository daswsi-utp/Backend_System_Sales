package com.microservice.orders.persistence;

import com.microservice.orders.entities.warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<warehouse, Long> {
}
