package com.microservice.sales.microservice_sales.service;

import client.ProductClient;
import client.RegistryClient;
import com.microservice.sales.microservice_sales.dto.*;
import com.microservice.sales.microservice_sales.entities.Registry;
import com.microservice.sales.microservice_sales.entities.SalesProduct;
import com.microservice.sales.microservice_sales.entities.Sales;
import com.microservice.sales.microservice_sales.entities.SalesProductKey;
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
    @Autowired
    private RegistryClient registryClient;

    @Override
    public List<SaleResponseDTO> findAll() {
        List<Sales> salesList = salesRepository.findAll();
        List<SaleResponseDTO> responseList = new ArrayList<>();

        for(Sales sale : salesList){
            RegistryDTO registryDTO = registryClient.getRegistryById(sale.getRegistry().getIdRegistry());
            List<SalesProductDTO> products = getSaleProductDetails(sale.getIdSale());
            SaleResponseDTO responseDTO = SaleResponseDTO.builder()
                    .idSale(sale.getIdSale())
                    .sum(sale.getSum())
                    .tax(sale.getTax())
                    .registry(registryDTO)
                    .relatedProducts(products)
                    .build();
            responseList.add(responseDTO);
        }
        return responseList;
    }

    @Override
    public SaleResponseDTO findById(Long id) {
        Sales sale = salesRepository.findById(id).orElseThrow();
        RegistryDTO registryDTO = registryClient.getRegistryById(sale.getRegistry().getIdRegistry());
        List<SalesProductDTO> products = getSaleProductDetails(sale.getIdSale());
        return SaleResponseDTO.builder()
                .idSale(sale.getIdSale())
                .sum(sale.getSum())
                .tax(sale.getTax())
                .registry(registryDTO)
                .relatedProducts(products)
                .build();
    }

    @Override
    public void save(SaleRequestDTO saleRequestDTO) {
        Sales sale = new Sales();
        sale.setSum(saleRequestDTO.getSum());
        sale.setTax(saleRequestDTO.getTax());

        Registry registry = new Registry();
        registry.setIdRegistry(saleRequestDTO.getRegistryId());
        sale.setRegistry(registry);

        Sales savedSale = salesRepository.save(sale);

        for(ProductQuantityDTO productInfo : saleRequestDTO.getProducts()){
            SalesProduct salesProduct = new SalesProduct();
            SalesProductKey key = new SalesProductKey();
            key.setSaleId(savedSale.getIdSale());
            key.setProductId(productInfo.getProductId());
            salesProduct.setId(key);
            salesProduct.setSale(savedSale);
            salesProduct.setQuantity(productInfo.getQuantity());
            salesProductRepository.save(salesProduct);
        }
    }
// Redundant method. Might have future use.
//    @Override
//    public List<SalesProduct> findSaleProductsBySaleId(Long saleId) {
//        return salesProductRepository.findBySaleIdSale(saleId);
//    }

    @Override
    public List<SalesProductDTO> getSaleProductDetails(Long saleId) {
        List<SalesProduct> salesProducts = salesProductRepository.findBySaleIdSale(saleId);
        List<SalesProductDTO> salesProductDTOS = new ArrayList<>();

        for(SalesProduct sp : salesProducts){
            Long productId = sp.getId().getProductId();
            ProductDTO productDTO = productClient.getProductById(productId);
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
