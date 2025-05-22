package com.microservice.inventory.persistence;

import com.microservice.inventory.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Product> findByNameProductContainingIgnoreCase(String name);
    List<Product> findByCategory_NameCategoryIgnoreCase(String categoryName);
}
