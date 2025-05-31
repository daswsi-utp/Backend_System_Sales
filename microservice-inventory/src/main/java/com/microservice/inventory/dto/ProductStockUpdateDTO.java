package com.microservice.inventory.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockUpdateDTO {
    private Long productId;
    private int quantity;
}
