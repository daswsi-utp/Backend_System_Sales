package com.microservice.orders.controller;

import com.microservice.orders.entities.*;
import com.microservice.orders.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrder (@RequestBody Order order){orderService.save(order);
    }
     @GetMapping("/all")
     public ResponseEntity<?>findAllOrder(){
         return ResponseEntity.ok(orderService.findAll());
    }
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)    {
        return  ResponseEntity.ok(orderService.findById(id));
    }

}
