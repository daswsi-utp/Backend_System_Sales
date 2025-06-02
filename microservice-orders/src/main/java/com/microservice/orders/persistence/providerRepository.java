package com.microservice.orders.persistence;

import com.microservice.orders.entities.provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface providerRepository extends JpaRepository<provider,Long> {
}
