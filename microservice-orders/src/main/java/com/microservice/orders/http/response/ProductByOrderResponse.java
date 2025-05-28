package com.microservice.orders.http.response;
import com.microservice.orders.dto.ProductDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductByOrderResponse {
    private String nameStaff;
    private Double total;
    private Timestamp dateTime;
    private String status;
    private List<ProductDTO> productDTOList;

}
