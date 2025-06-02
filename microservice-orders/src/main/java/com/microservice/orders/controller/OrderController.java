package com.microservice.orders.controller;

import com.microservice.orders.dto.OrderRequestDTO;
import com.microservice.orders.dto.UserDTO;
import com.microservice.orders.entities.provider;
import com.microservice.orders.entities.warehouse;
import com.microservice.orders.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IOrderService userService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllOrder() {
        return ResponseEntity.ok(iOrderService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(iOrderService.findById(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        iOrderService.save(orderRequestDTO);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> getProductsByOrder(@PathVariable Long id) {
        return ResponseEntity.ok(iOrderService.getOrderProductDetails(id));
    }

    @GetMapping("/warehouses")
    public ResponseEntity<List<warehouse>> getAllWarehouses() {
        List<warehouse> warehouses = iOrderService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    // Endpoint para obtener todos los providers
    @GetMapping("/providers")
    public ResponseEntity<List<provider>> getAllProviders() {
        List<provider> providers = iOrderService.getAllProviders();
        return ResponseEntity.ok(providers);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers(); // Llamar al método del servicio
        return ResponseEntity.ok(users);
    }
}


