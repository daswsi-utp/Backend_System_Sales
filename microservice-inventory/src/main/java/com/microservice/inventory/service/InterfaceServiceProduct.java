package com.microservice.inventory.service;

import com.microservice.inventory.entities.Product;

import java.util.List;
import java.util.Optional;

public interface InterfaceServiceProduct {

    List<Product> getAllProducts();
    List<Product> searchProductByName(String name);
    List<Product> getProductsByCategory(String categoryName);
    Optional<Product> getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);

}
