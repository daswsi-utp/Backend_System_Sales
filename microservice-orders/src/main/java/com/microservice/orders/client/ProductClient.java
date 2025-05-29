package com.microservice.orders.client;

import com.microservice.orders.dto.ProductDTO;
import com.microservice.orders.dto.ProductQuantityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@FeignClient(name = "micro-inventory", url = "http://localhost:8090/api/products")
public interface ProductClient {
    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);
    @PutMapping("/stock/increase")
    void increaseStock (@RequestBody List<ProductQuantityDTO> stockUpdates);
    }

