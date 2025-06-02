package com.microservice.inventory.service;

import com.microservice.inventory.dto.ProductStockUpdateDTO;
import com.microservice.inventory.entities.Product;
import com.microservice.inventory.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProductImpl implements IServiceProduct {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchProductByName(String name) {
        return productRepository.findByNameProductContainingIgnoreCase(name);
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategory_NameCategoryIgnoreCase(categoryName);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product prodToUpdate = existingProduct.get();
            prodToUpdate.setNameProduct(product.getNameProduct());
            prodToUpdate.setPriceProduct(product.getPriceProduct());
            prodToUpdate.setQuantityProduct(product.getQuantityProduct());
            prodToUpdate.setBrand(product.getBrand());
            prodToUpdate.setCategory(product.getCategory());
            return productRepository.save(prodToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void decreaseStock(List<ProductStockUpdateDTO> stockUpdates) {
        for(ProductStockUpdateDTO update : stockUpdates){
            Product product = productRepository.findById(update.getProductId()).orElseThrow(() -> new RuntimeException("Product not found with this ID: " + update.getProductId()));
            Integer currentQuantity = product.getQuantityProduct();
            if(currentQuantity == null){
                throw new IllegalStateException("Product quantity error for product ID: " + product.getIdProduct());
            }
            int newQuantity = product.getQuantityProduct() - update.getQuantity();
            if(newQuantity < 0){
                throw new IllegalStateException("Insufficient stock");
            }

            product.setQuantityProduct(newQuantity);
            productRepository.save(product);
        }
    }
    @Override
    public void increaseStock(List<ProductStockUpdateDTO> stockUpdates) {
        for (ProductStockUpdateDTO update : stockUpdates) {
            Product product = productRepository.findById(update.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with this ID: " + update.getProductId()));
            Integer currentQuantity = product.getQuantityProduct();
            if (currentQuantity == null) {
                throw new IllegalStateException("Product quantity error for product ID: " + product.getIdProduct());
            }
            int newQuantity = currentQuantity + update.getQuantity(); // Aumentar la cantidad
            product.setQuantityProduct(newQuantity);
            productRepository.save(product);
        }
    }
}
