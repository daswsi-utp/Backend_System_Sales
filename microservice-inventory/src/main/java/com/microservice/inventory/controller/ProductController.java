package com.microservice.inventory.controller;

import com.microservice.inventory.dto.ProductStockUpdateDTO;
import com.microservice.inventory.entities.Product;
import com.microservice.inventory.persistence.ProductRepository;
import com.microservice.inventory.service.IServiceProduct;
import com.microservice.inventory.service.ServiceProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/compoundSearch")
    public List<Product> compoundSearch(@RequestParam(required = false) String name, @RequestParam(required = false) String category, @RequestParam(required = false) String brand){
        return iServiceProduct.compoundSearch(name, category, brand);
    }

    @GetMapping("/find/{name}")
    public List<Product> searchProductByName(@PathVariable String name){
        return iServiceProduct.searchProductByName(name);

    }
    @GetMapping("/category/{categoryName}")
    public List<Product> getProductByCategory(@PathVariable String categoryName)
    {
        return iServiceProduct.getProductsByCategory(categoryName);
    }
    @GetMapping("/brand/{brandName}")
    public List<Product> getProductByBrand(@PathVariable String brandName){
        return iServiceProduct.getProductByBrand(brandName);
    }
    @PutMapping("/stock/decrease")
    public ResponseEntity<?> decreaseStock(@RequestBody List<ProductStockUpdateDTO> stockUpdateList){
        iServiceProduct.decreaseStock(stockUpdateList);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/stock/increase")
    public ResponseEntity<?> increaseStock(@RequestBody List<ProductStockUpdateDTO> stockUpdateList){
        iServiceProduct.increaseStock(stockUpdateList);
        return ResponseEntity.ok().build();
    }



}
