package com.service;

import com.entities.Sales;

import java.util.List;

public interface Interface_SalesMS {
    List<Sales> findAll();
    Sales findById(String id);
    void save(Sales sale);
    List<Sales> findByIdSales(String id);
}
