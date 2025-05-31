package com.microservice.sales.microservice_sales.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalesProductDTO {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}
