package com.microservice.sales.microservice_sales.service;

import com.microservice.sales.microservice_sales.entities.Sales;
import com.microservice.sales.microservice_sales.persistence.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImp implements ISalesService{
    @Autowired
    private SalesRepository salesRepository;
    @Override
    public List<Sales> findAll() {
        return (List<Sales>) salesRepository.findAll();
    }

    @Override
    public Sales findById(Long id) {
        return salesRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Sales sale) {
        salesRepository.save(sale);
    }
}
