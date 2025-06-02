package com.microservice.orders.controller;

import com.microservice.orders.dto.OrderRequestDTO;
import com.microservice.orders.dto.OrderResponseDTO;
import com.microservice.orders.enums.OrderStatus;
import com.microservice.orders.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, Integer> body) {
        try {
            int status = body.get("status");
            OrderStatus orderStatus = OrderStatus.fromValue(status);
            OrderResponseDTO updatedOrder = iOrderService.updateOrderStatus(id, orderStatus);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating order status");
        }
    }



}
