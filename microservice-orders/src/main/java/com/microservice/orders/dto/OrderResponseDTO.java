package com.microservice.orders.dto;

import com.microservice.orders.entities.provider;
import com.microservice.orders.entities.warehouse;
import com.microservice.orders.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDTO {
    private Long id; // Agrega este campo para el ID de la orden
    private warehouse warehouse;
    private provider provider;
    private RegistryDTO registry;
    private List<OrderProductDTO> relatedProducts;
    private BigDecimal sum;
    private int  status;
}
