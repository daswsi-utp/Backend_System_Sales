package com.service;

import com.entities.Sales;

import java.util.List;

public interface ISaleService {
    List<Sales> findAll();
    Sales findById(Long id);
    void save(Sales sale);
    List<Sales> findByIdSales(Long id);
}
