package com.microservice.inventory.service;

import com.microservice.inventory.dto.ProductStockUpdateDTO;
import com.microservice.inventory.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IServiceProduct {

    List<Product> getAllProducts();
    List<Product> searchProductByName(String name);
    List<Product> getProductsByCategory(String categoryName);
    List<Product> getProductByBrand(String brandName);
    Optional<Product> getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);
    void decreaseStock(List<ProductStockUpdateDTO> stockUpdates);

    //jack
    void increaseStock(List<ProductStockUpdateDTO> stockUpdates);

}
