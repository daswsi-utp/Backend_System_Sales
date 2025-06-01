package com.microservice.orders.dto;
import com.microservice.orders.entities.provider;
import com.microservice.orders.entities.warehouse;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDTO {
    private warehouse warehouse;
    private provider provider;

    private Long registryId;
    private List<ProductQuantityDTO>products;
    private BigDecimal sum;
    private String status;
}
