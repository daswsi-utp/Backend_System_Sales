package com.microservice.sales.microservice_sales.persistence;

import com.microservice.sales.microservice_sales.entities.SalesProduct;
import com.microservice.sales.microservice_sales.entities.SalesProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesProductRepository extends JpaRepository<SalesProduct, SalesProductKey> {
    List<SalesProduct> findBySaleId(Long id);
}
