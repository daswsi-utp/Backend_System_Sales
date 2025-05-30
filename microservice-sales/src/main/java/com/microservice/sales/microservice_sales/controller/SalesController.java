package com.microservice.sales.microservice_sales.controller;

import com.microservice.sales.microservice_sales.dto.SaleRequestDTO;
import com.microservice.sales.microservice_sales.entities.Sales;
import com.microservice.sales.microservice_sales.service.ISalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/sales")
public class SalesController {

    @Autowired
    private ISalesService iSalesService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllSales(){
        return ResponseEntity.ok(iSalesService.findAll());
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findSalesById(@PathVariable Long id){
        return ResponseEntity.ok(iSalesService.findById(id));
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSale(@RequestBody SaleRequestDTO salesRequestDTO){
        iSalesService.save(salesRequestDTO);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> getProductsBySale(@PathVariable Long id){
       return ResponseEntity.ok(iSalesService.getSaleProductDetails(id));
    }
}
