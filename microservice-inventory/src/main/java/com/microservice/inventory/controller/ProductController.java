package com.microservice.inventory.controller;

import com.microservice.inventory.entities.Product;
import com.microservice.inventory.persistence.ProductRepository;
import com.microservice.inventory.service.IServiceProduct;
import com.microservice.inventory.service.ServiceProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IServiceProduct iServiceProduct;

    @GetMapping
    public List<Product> getallProducts(){
        return iServiceProduct.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){
        return iServiceProduct.getProductById(id);

    }
    @PostMapping
    public Product createProduct (@RequestBody Product product){
        return iServiceProduct.createProduct(product);

    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return iServiceProduct.updateProduct(id, product);

    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        return iServiceProduct.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<Product> searchProductByName(@RequestParam String name){
        return iServiceProduct.searchProductByName(name);

    }
    @GetMapping("/category/{categoryName}")
    public List<Product> getProductByCategory(@PathVariable String categoryName)
    {
        return iServiceProduct.getProductsByCategory(categoryName);

    }

}
