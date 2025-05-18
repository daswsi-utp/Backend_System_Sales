package com.controller;

import com.entities.Sales;
import com.service.Interface_SalesMS;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    @Autowired
    private Interface_SalesMS interfaceSalesMs;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSales(@RequestBody Sales sale){
        interfaceSalesMs.save(sale);
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllSales(){
        return ResponseEntity.ok(interfaceSalesMs.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        return ResponseEntity.ok(interfaceSalesMs.findByIdSales(id));
    }

    //TODO-Change later interfaceSaleMs to the actual service of product when implemented
    @GetMapping("/searchProduct/{id}")
    public ResponseEntity<?> findByProductId(@PathVariable String id){
        return ResponseEntity.ok(interfaceSalesMs.findById(id));
    }
}

