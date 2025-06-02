package com.microservice.inventory.persistence;

import com.microservice.inventory.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Product> findByNameProductContainingIgnoreCase(String name);
    List<Product> findByCategory_NameCategoryIgnoreCase(String categoryName); /*AddIgnoreCase*/
    List<Product> findByBrand_NameBrandIgnoreCase(String brandName);
    //This method is a advanced search where the parameters for name, category and brand can be null
    @Query("SELECT p FROM Product p " +
            "WHERE (:name IS NULL OR LOWER(p.nameProduct) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:category IS NULL OR LOWER(p.category.nameCategory) = LOWER(:category)) " +
            "AND (:brand IS NULL OR LOWER(p.brand.nameBrand) = LOWER(:brand))")
    List<Product> compoundSearch(@Param("name") String name, @Param("category")String categoryName, @Param("brand")String brandName);

}

