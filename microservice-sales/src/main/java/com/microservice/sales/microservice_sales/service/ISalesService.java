package com.microservice.sales.microservice_sales.service;
import com.microservice.sales.microservice_sales.dto.SaleRequestDTO;
import com.microservice.sales.microservice_sales.dto.SalesProductDTO;
import com.microservice.sales.microservice_sales.entities.SalesProduct;
import com.microservice.sales.microservice_sales.entities.Sales;

import java.util.List;

public interface ISalesService {
    List<Sales> findAll();
    Sales findById(Long id);
    void save(SaleRequestDTO saleRequestDTO);

    List<SalesProduct> findSaleProductsBySaleId(Long saleId);
    List<SalesProductDTO> getSaleProductDetails(Long saleId);
}
