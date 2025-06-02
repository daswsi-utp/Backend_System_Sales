package com.microservice.registry.microservice_registry.client;

import com.microservice.registry.microservice_registry.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-users", url = "http://localhost:8080/api/users")
public interface UserClient {
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id);
}
