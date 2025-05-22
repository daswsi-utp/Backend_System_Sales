package com.microservice.sales.microservice_sales.service;

import client.ProductClient;
import com.microservice.sales.microservice_sales.dto.ProductDTO;
import com.microservice.sales.microservice_sales.dto.SalesProductDTO;
import com.microservice.sales.microservice_sales.entities.SalesProduct;
import com.microservice.sales.microservice_sales.entities.Sales;
import com.microservice.sales.microservice_sales.persistence.SalesProductRepository;
import com.microservice.sales.microservice_sales.persistence.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesServiceImp implements ISalesService{
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private SalesProductRepository salesProductRepository;
    @Autowired
    private ProductClient productClient;

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
    public List<SalesProduct> findSaleProductsBySaleId(Long saleId) {
        return salesProductRepository.findBySaleId(saleId);
    }

    @Override
    public List<SalesProductDTO> getSaleProductDetails(Long saleId) {
        List<SalesProduct> salesProducts = salesProductRepository.findBySaleId(saleId);
        List<SalesProductDTO> salesProductDTOS = new ArrayList<>();

        for(SalesProduct sp : salesProducts){
            ProductDTO productDTO = productClient.getProductById(sp.getProduct().getIdProduct());
            SalesProductDTO salesProductDTO = new SalesProductDTO();
            salesProductDTO.setProductId(productDTO.getIdProduct());
            salesProductDTO.setProductName(productDTO.getNameProduct());
            salesProductDTO.setPrice(productDTO.getPriceProduct());
            salesProductDTO.setQuantity(productDTO.getQuantityProduct());
            salesProductDTOS.add(salesProductDTO);
        }
        return salesProductDTOS;
    }
}
