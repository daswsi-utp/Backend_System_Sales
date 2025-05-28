package com.microservice.orders.client;

import com.microservice.orders.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient(name = "micro-inventory", url = "localhost:8090")
public interface ProductClient {

    @GetMapping("/api/products/search-by-order/{orderId}")
    List<ProductDTO> findAllProductByOrder(@PathVariable Long orderId);


}
