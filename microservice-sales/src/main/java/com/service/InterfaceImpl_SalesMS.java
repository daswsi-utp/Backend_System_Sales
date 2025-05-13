package com.service;

import com.entities.Sales;
import com.persistence.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterfaceImpl_SalesMS implements Interface_SalesMS{
    @Autowired
    private SalesRepository salesRepository;


    @Override
    public List<Sales> findAll() {
        return (List<Sales>) salesRepository.findAll();
    }

    @Override
    public Sales findById(String id) {
        return salesRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Sales sale) {
        salesRepository.save(sale);
    }

    @Override
    public List<Sales> findByIdSales(String id) {
        return salesRepository.findAllBySalesId(id);
    }
}
