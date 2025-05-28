package com.microservice.inventory.service;

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
            prodToUpdate.setDescriptionProduct(product.getDescriptionProduct());
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


    //jack
    @Override
    public List<Product> findByOrderId(Long orderId) {
        return productRepository.findAllByOrderId(orderId);
        }

    //jack
}
