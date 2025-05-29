package com.microservice.registry.microservice_registry.persistence;

import com.microservice.registry.microservice_registry.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
