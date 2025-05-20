package com.service;

import com.entities.Sales;
import com.persistence.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISalesServiceImpl implements ISaleService {
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

    @Override
    public List<Sales> findByIdSales(Long id) {
        return salesRepository.findAllBySalesId(id);
    }
}
