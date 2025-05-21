package com.microservice.sales.microservice_sales.persistence;

import com.microservice.sales.microservice_sales.entities.Sales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends CrudRepository<Sales, Long> {

    List<Sales> findAll(Long idSales);
}
