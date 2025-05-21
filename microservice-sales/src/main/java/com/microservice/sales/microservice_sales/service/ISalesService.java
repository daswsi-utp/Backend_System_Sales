package com.microservice.sales.microservice_sales.service;
import com.microservice.sales.microservice_sales.entities.Sales;

import java.util.List;

public interface ISalesService {
    List<Sales> findAll();
    Sales findById(Long id);
    void save(Sales sale);
}
