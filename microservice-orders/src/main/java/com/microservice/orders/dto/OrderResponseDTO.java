package com.microservice.orders.dto;

import com.microservice.orders.entities.provider;
import com.microservice.orders.entities.warehouse;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponseDTO {
    private warehouse warehouse;
    private provider provider;
    private RegistryDTO registry;
    private List<OrderProductDTO> relatedProducts;
    private BigDecimal sum;
    private String status;
}
