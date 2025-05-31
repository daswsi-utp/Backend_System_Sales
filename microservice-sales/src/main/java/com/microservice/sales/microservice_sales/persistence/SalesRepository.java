package com.microservice.sales.microservice_sales.persistence;

import com.microservice.sales.microservice_sales.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

}
