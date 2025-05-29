package com.microservice.orders.client;

import com.microservice.orders.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient(name = "micro-inventory", url = "http://localhost:8090/api/products")
public interface ProductClient {
    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);
    //@PutMapping("/stock/decrease")
    //void decreaseStock(@RequestBody List<ProductQuantityDTO> stockUpdates);


}
