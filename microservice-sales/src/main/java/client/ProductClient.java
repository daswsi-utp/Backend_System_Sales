package client;

import com.microservice.sales.microservice_sales.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-inventory", url = "localhost:8090/api/inventory")
public interface ProductClient {
    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);
}
