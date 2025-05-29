package com.microservice.sales.microservice_sales.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductQuantityDTO {
    private Long productId;
    private int quantity;
}
