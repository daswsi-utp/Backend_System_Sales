package com.microservice.orders.controller;

import com.microservice.orders.dto.OrderRequestDTO;
import com.microservice.orders.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllOrder(){
        return ResponseEntity.ok(iOrderService.findAll());
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable Long id){
        return ResponseEntity.ok(iOrderService.findById(id));
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        iOrderService.save(orderRequestDTO);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> getProductsByOrder(@PathVariable Long id){
        return ResponseEntity.ok(iOrderService.getOrderProductDetails(id));
    }
}
