package com.microservice.orders.client;

import com.microservice.orders.dto.RegistryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-registry", url = "http://localhost:9091/api/registry")
public interface RegistryClient {
    @GetMapping("find/{id}")
    RegistryDTO getRegistryById(@PathVariable Long id);
}
