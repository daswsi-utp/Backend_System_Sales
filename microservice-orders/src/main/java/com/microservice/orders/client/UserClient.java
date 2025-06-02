package com.microservice.orders.client;
import com.microservice.orders.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-users", url = "http://localhost:8092/api/users")
public interface UserClient {
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id);
    @GetMapping()
    List<UserDTO> getAllUsers();

}



