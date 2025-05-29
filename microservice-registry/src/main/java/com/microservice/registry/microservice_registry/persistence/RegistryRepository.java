package com.microservice.registry.microservice_registry.persistence;

import com.microservice.registry.microservice_registry.entitites.Registry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistryRepository extends JpaRepository<Registry, Long> {
}
