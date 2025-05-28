package client;

import com.microservice.sales.microservice_sales.dto.ProductDTO;
import com.microservice.sales.microservice_sales.dto.ProductQuantityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "micro-inventory", url = "http://localhost:8090/api/products")
public interface ProductClient {
    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);
    @PutMapping("/stock/decrease")
    void decreaseStock(@RequestBody List<ProductQuantityDTO> stockUpdates);
}
